package net.lugo.utools.mixin;

import net.lugo.utools.UTools;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.Properties.class)

public abstract class ClientTimeMixin {
    @Inject(at = @At("RETURN"), method = "getTimeOfDay", cancellable = true)
    public void getTimeOfDay(CallbackInfoReturnable<Long> cir) {
        switch (UTools.CONFIG.clientTimeType) {
            case Disabled -> cir.cancel();
            case Day -> cir.setReturnValue((long) 1000);
            case Noon -> cir.setReturnValue((long) 6000);
            case Night -> cir.setReturnValue((long) 13000);
            case Midnight -> cir.setReturnValue((long) 18000);
        }
    }
}