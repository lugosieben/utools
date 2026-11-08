package net.lugo.utools.mixin.visualmods;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
public class LowFireOverlayMixin {
    @Inject(method = "submitFire", at = @At("HEAD"))
    private static void renderFireOverlay(PoseStack matrices, SubmitNodeCollector vertexConsumers, TextureAtlasSprite sprite, CallbackInfo ci) {
        if (Minecraft.getInstance().player == null) return;

        if (ModConfig.hideFireWhenResistant && Minecraft.getInstance().player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
            matrices.translate(0, -100, 0);
        } else {
            matrices.translate(0, (double) ModConfig.lowFireModifier / 100, 0);
        }
    }
}
