package net.lugo.utools.mixin.hideplayers;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.lugo.utools.features.HidePlayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class HidePlayersMixin {
    // TODO: Implement this for 1.21.3+
    /*
    @ModifyReturnValue(method = "shouldRenderFeatures(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;)Z", at = @At("HEAD"))
    private void shouldRenderFeatures(PlayerEntityRenderState playerEntityRenderState, CallbackInfoReturnable<Boolean> cir) {
        if (HidePlayers.get() && MinecraftClient.getInstance().player != null) {
            if (MinecraftClient.getInstance().player.getName() != playerEntityRenderState.playerName) cir.setReturnValue(false);
        }
    }
    */
}
