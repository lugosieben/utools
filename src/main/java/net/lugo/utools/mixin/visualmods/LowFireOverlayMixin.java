package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class LowFireOverlayMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"))
    private static void renderFireOverlay(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Sprite sprite, CallbackInfo ci) {
        if (MinecraftClient.getInstance().player == null) return;

        if (UTools.getConfig().hideFireWhenResistant && MinecraftClient.getInstance().player.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            matrices.translate(0, -100, 0);
        } else {
            matrices.translate(0, (double) UTools.getConfig().lowFireModifier / 100, 0);
        }
    }
}
