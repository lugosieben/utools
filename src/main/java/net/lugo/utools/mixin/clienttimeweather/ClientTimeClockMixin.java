package net.lugo.utools.mixin.clienttimeweather;

import net.lugo.utools.features.ClientTime;
import net.minecraft.client.ClientClockManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientClockManager.ClientClockInstance.class)
public abstract class ClientTimeClockMixin {
    @Inject(at = @At("RETURN"), method = "totalTicks", cancellable = true)
    public void totalTicks(CallbackInfoReturnable<Long> cir) {
        Long forcedTime = ClientTime.forcedGameTime();
        if (forcedTime != null) {
            cir.setReturnValue(forcedTime);
        }
    }
}
