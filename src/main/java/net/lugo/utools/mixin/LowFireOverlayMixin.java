package net.lugo.utools.mixin;

import net.lugo.utools.UTools;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class LowFireOverlayMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"))
    private static void renderFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        matrices.translate(0, (double) UTools.getConfig().lowFireModifier / 100, 0);
    }
}
