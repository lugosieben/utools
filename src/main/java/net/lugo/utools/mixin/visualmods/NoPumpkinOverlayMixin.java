package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class NoPumpkinOverlayMixin {

    @Inject(
        method = "renderCameraOverlays",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;renderTextureOverlay(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/resources/Identifier;F)V",
            shift = At.Shift.BY),
        cancellable = true
    )
    private void renderOverlay (GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        if (UTools.getConfig().hidePumpkinOverlay) ci.cancel();
    }
}
