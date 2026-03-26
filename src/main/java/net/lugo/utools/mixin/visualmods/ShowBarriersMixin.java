package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.config.ModConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ShowBarriersMixin {
    @Inject(method = "getMarkerParticleTarget", at = @At("RETURN"), cancellable = true)
    public void getBlockParticle(CallbackInfoReturnable<Block> cir) {
        if ((ModConfig.showBarriers) && (cir.getReturnValue() == null)) {
            cir.setReturnValue(Blocks.BARRIER);
        }
    }
}
