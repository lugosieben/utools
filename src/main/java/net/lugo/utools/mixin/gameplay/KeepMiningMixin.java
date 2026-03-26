package net.lugo.utools.mixin.gameplay;

import net.lugo.utools.config.ModConfig;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class KeepMiningMixin {
    @Shadow private BlockPos destroyBlockPos;

    @Inject(method = "sameDestroyTarget", at = @At("HEAD"), cancellable = true)
    private void isCurrentlyBreaking(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.keepMining) cir.setReturnValue(pos.equals(this.destroyBlockPos));
    }
}
