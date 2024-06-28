package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MapInBoatMixin {
    @Inject(method = "tickRiding()V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/vehicle/BoatEntity;setInputs(ZZZZ)V",
            shift = At.Shift.AFTER),
            cancellable = true)
    private void afterBoatMovement(CallbackInfo ci) {
        if (UTools.getConfig().mapInBoat) ci.cancel();
    }
}