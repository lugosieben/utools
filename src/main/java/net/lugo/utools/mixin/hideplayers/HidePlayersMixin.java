package net.lugo.utools.mixin.hideplayers;

import net.lugo.utools.features.HidePlayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class HidePlayersMixin {
    @Inject(method = "shouldRender", at = @At("RETURN"), cancellable = true)
    private void shouldRender(Entity entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (HidePlayers.get() && entity.isPlayer() && entity != MinecraftClient.getInstance().player) cir.setReturnValue(false);
    }
}
