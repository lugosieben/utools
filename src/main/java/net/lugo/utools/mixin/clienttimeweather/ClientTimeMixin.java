package net.lugo.utools.mixin.clienttimeweather;

import net.lugo.utools.config.ModConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.ClientLevelData.class)

public abstract class ClientTimeMixin {
    @Inject(at = @At("RETURN"), method = "getGameTime", cancellable = true)
    public void getTimeOfDay(CallbackInfoReturnable<Long> cir) {
        switch (ModConfig.clientTimeType) {
            case DISABLED -> cir.cancel();
            case DAY -> cir.setReturnValue((long) 1000);
            case NOON -> cir.setReturnValue((long) 6000);
            case NIGHT -> cir.setReturnValue((long) 13000);
            case MIDNIGHT -> cir.setReturnValue((long) 18000);
            case CUSTOM -> cir.setReturnValue((long) ModConfig.customClientTime);
        }
    }
}