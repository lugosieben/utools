package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.config.ModConfig;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class NoPumpkinOverlayMixin {

    @Inject(method = "extractRenderState", at = @At("HEAD"), cancellable = true)
    private void extract (GuiGraphicsExtractor context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (ModConfig.hidePumpkinOverlay) ci.cancel();
    }
}
