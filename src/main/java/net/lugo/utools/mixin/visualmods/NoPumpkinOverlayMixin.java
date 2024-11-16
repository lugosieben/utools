package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class NoPumpkinOverlayMixin {

    @Inject(
        method = "renderMiscOverlays",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V",
            shift = At.Shift.BY),
        cancellable = true
    )
    private void renderOverlay (DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (UTools.getConfig().hidePumpkinOverlay) ci.cancel();
    }
}
