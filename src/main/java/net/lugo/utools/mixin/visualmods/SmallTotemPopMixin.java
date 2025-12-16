package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ScreenEffectRenderer.class)
public class SmallTotemPopMixin {
    @ModifyVariable(method = "renderItemActivationAnimation", at = @At("STORE"), ordinal = 8)
    private float renderFloatingItem(float n) {
        return n * (UTools.getConfig().totemPopSize / 100);
    }
}
