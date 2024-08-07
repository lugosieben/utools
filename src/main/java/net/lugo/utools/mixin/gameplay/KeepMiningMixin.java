package net.lugo.utools.mixin.gameplay;

import net.lugo.utools.UTools;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class KeepMiningMixin {
    @Shadow private BlockPos currentBreakingPos;

    @Inject(method = "isCurrentlyBreaking", at = @At("HEAD"), cancellable = true)
    private void isCurrentlyBreaking(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (UTools.getConfig().keepMining) cir.setReturnValue(pos.equals(this.currentBreakingPos));
    }
}
