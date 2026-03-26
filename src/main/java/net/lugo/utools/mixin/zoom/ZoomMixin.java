package net.lugo.utools.mixin.zoom;

import net.lugo.utools.config.ModConfig;
import net.lugo.utools.features.Zoom;
import net.lugo.utools.util.Easing;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Camera.class)
public class ZoomMixin {
    @ModifyArgs(method = "calculateFov", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;modifyFovBasedOnDeathOrFluid(FF)F"))
    public void calculateFov(Args args) {
        getFovFromArgs(args);
    }

    @ModifyArgs(method = "calculateHudFov", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;modifyFovBasedOnDeathOrFluid(FF)F"))
    public void calculateHudFov(Args args) {
        getFovFromArgs(args);
    }

    @Unique
    private void getFovFromArgs(Args args) {
        float partialTicks = args.get(0);
        float fov = args.get(1);
        if (ModConfig.turnOffZoom) return;
        float effectiveZoomMultiplier = Zoom.goal;
        Easing easing = Zoom.lastGoal >= Zoom.goal ? Zoom.getZoomOutEasing() : Zoom.getZoomInEasing();

        if (Zoom.latestEffectiveZoom != Zoom.goal) {
            Zoom.t += (partialTicks * 50) / 1000;
            effectiveZoomMultiplier = easing.function.apply(
                    Zoom.lastGoal,
                    Zoom.goal,
                    Math.min(Zoom.t, ModConfig.zoomDuration) / ModConfig.zoomDuration
            );
        } else {
            Zoom.t = 0f;
        }

        Zoom.latestEffectiveZoom = effectiveZoomMultiplier;
        args.set(1, fov / effectiveZoomMultiplier);
    }
}
