package net.lugo.utools.mixin.mixinevents;

import net.lugo.utools.features.LightOverlay;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "renderLevel", at = @At("RETURN"))
    private void afterRender(CallbackInfo ci) {
        LightOverlay.renderEnd();
    }
}
