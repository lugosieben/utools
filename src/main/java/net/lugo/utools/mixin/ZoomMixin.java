package net.lugo.utools.mixin;

import net.lugo.utools.UTools;
import net.lugo.utools.features.Zoom;
import net.minecraft.client.MinecraftClient;
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
    MinecraftClient MC = MinecraftClient.getInstance();
    @Unique
    double latestZoomMultiplier = 1.0;
    @Unique
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    @Inject(at = @At("RETURN"), method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D",cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        boolean isZooming = Zoom.isZooming();
        double zoomMultiplier = 1.0;
        double fov = callbackInfo.getReturnValue();

        if (isZooming) {
            // Prevent Zoom values at 0 or below
            int ConfigZoomMultiplier = AutoConfig.getConfigHolder(ModConfig.class).getConfig().zoomMultiplier;
            zoomMultiplier = ConfigZoomMultiplier > 0 ? (double) ConfigZoomMultiplier : 1.0;
            MC.options.smoothCameraEnabled = true;
        } else MC.options.smoothCameraEnabled = false;

        double effectiveZoomMultiplier = lerp(latestZoomMultiplier, zoomMultiplier, config.zoomSpeed);
        latestZoomMultiplier = effectiveZoomMultiplier;
        callbackInfo.setReturnValue(fov / effectiveZoomMultiplier);
    }
}
