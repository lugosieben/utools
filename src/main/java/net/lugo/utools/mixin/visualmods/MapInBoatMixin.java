package net.lugo.utools.mixin.visualmods;

import net.lugo.utools.UTools;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class MapInBoatMixin {
    @Inject(method = "rideTick()V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/vehicle/boat/AbstractBoat;setInput(ZZZZ)V",
            shift = At.Shift.AFTER),
            cancellable = true)
    private void afterBoatMovement(CallbackInfo ci) {
        if (UTools.getConfig().mapInBoat) ci.cancel();
    }
}