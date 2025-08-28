package net.lugo.utools.mixin.zoom;

import net.lugo.utools.UTools;
import net.lugo.utools.features.Zoom;
import net.lugo.utools.util.Easing;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class ZoomMixin {
    @Inject(at = @At("RETURN"), method = "getFov", cancellable = true)
    public void getFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> callbackInfo) {
        if (UTools.getConfig().turnOffZoom) return;
        float fov = callbackInfo.getReturnValue();
        float effectiveZoomMultiplier = Zoom.goal;
        Easing easing = Zoom.lastGoal >= Zoom.goal ? UTools.getConfig().getZoomOutEasing() : UTools.getConfig().getZoomInEasing();
        
        if (Zoom.latestEffectiveZoom != Zoom.goal) {
            Zoom.t += (tickDelta * 50) / 1000;
            effectiveZoomMultiplier = easing.function.apply(
                Zoom.lastGoal,
                Zoom.goal,
                Math.min(Zoom.t, UTools.getConfig().zoomDuration) / UTools.getConfig().zoomDuration
            );
        } else {
            Zoom.t = 0f;
        }
        
        Zoom.latestEffectiveZoom = effectiveZoomMultiplier;
        callbackInfo.setReturnValue(fov / effectiveZoomMultiplier);
    }
}
