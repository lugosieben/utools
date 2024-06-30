package net.lugo.utools.mixin.zoom;

import net.lugo.utools.UTools;
import net.lugo.utools.features.Zoom;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class ZoomMixin {
    @Inject(at = @At("RETURN"), method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", cancellable = true)
    public void getFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> callbackInfo) {
        double fov = callbackInfo.getReturnValue();
        double effectiveZoomMultiplier = Zoom.goal;
        
        if (Zoom.latestEffectiveZoom != Zoom.goal) {
            Zoom.t += (tickDelta * 50) / 1000;
            effectiveZoomMultiplier = UTools.getConfig().zoomEasing.function.apply(
                (float) Zoom.lastGoal,
                (float) Zoom.goal,
                Math.min(Zoom.t, UTools.getConfig().zoomDuration) / UTools.getConfig().zoomDuration
            );
        } else {
            Zoom.t = 0f;
        }
        
        Zoom.latestEffectiveZoom = effectiveZoomMultiplier;
        callbackInfo.setReturnValue(fov / effectiveZoomMultiplier);
    }
}
