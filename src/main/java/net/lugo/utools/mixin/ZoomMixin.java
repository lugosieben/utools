package net.lugo.utools.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import net.lugo.utools.config.ModConfig;
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
    MinecraftClient MC = MinecraftClient.getInstance();

    @Inject(at = @At("RETURN"), method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D",cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        if (Zoom.isZooming()){
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov / AutoConfig.getConfigHolder(ModConfig.class).getConfig().zoomMultiplier);
            MC.options.smoothCameraEnabled = true;
        } else {
            MC.options.smoothCameraEnabled = false;
        }
    }
}
