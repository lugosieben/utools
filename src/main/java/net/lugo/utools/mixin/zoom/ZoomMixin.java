package net.lugo.utools.mixin.zoom;

import net.lugo.utools.UTools;
import net.lugo.utools.features.Zoom;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class ZoomMixin {
    @Unique
    private double lerp(double a, double b, double f)
    {
        return (a * (1.0 - f)) + (b * f);
    }

    @Unique
    double latestZoom = 1.0;
    
    @Inject(at = @At("RETURN"), method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", cancellable = true)
    public void getFov(CallbackInfoReturnable<Double> callbackInfo) {
        double fov = callbackInfo.getReturnValue();

        double effectiveZoomMultiplier = lerp(latestZoom, Zoom.goal, UTools.getConfig().zoomSpeed);
        latestZoom = effectiveZoomMultiplier;
        callbackInfo.setReturnValue(fov / effectiveZoomMultiplier);
    }
}
