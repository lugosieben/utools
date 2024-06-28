package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class NoPumpkinOverlayMixin {
    @Shadow @Final private static Identifier PUMPKIN_BLUR;

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private void renderOverlay (DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        if (texture == PUMPKIN_BLUR && UTools.getConfig().hidePumpkinOverlay) ci.cancel();
    }
}
