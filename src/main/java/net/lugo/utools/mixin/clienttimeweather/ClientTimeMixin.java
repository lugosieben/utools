package net.lugo.utools.mixin.clienttimeweather;

import net.lugo.utools.features.ClientTime;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.ClientLevelData.class)

public abstract class ClientTimeMixin {
    @Inject(at = @At("RETURN"), method = "getGameTime", cancellable = true)
    public void getTimeOfDay(CallbackInfoReturnable<Long> cir) {
        Long forcedTime = ClientTime.forcedGameTime();
        if (forcedTime != null) {
            cir.setReturnValue(forcedTime);
        }
    }
}