package net.lugo.utools.mixin.visualmods;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lugo.utools.UTools;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class LowFireOverlayMixin {
    @Inject(method = "renderFire", at = @At("HEAD"))
    private static void renderFireOverlay(PoseStack matrices, MultiBufferSource vertexConsumers, TextureAtlasSprite sprite, CallbackInfo ci) {
        if (Minecraft.getInstance().player == null) return;

        if (UTools.getConfig().hideFireWhenResistant && Minecraft.getInstance().player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
            matrices.translate(0, -100, 0);
        } else {
            matrices.translate(0, (double) UTools.getConfig().lowFireModifier / 100, 0);
        }
    }
}
