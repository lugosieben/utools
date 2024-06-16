package net.lugo.utools.mixin;

import net.lugo.utools.UTools;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class ClientWeatherMixin {

    @Inject(method = "getRainGradient", at = @At("HEAD"), cancellable = true)
    public void getRainGradient(float delta, CallbackInfoReturnable<Float> cir) {
        if (UTools.CONFIG.clientWeatherClear) cir.setReturnValue(0F);

        cir.cancel();
    }

    @Inject(method = "getThunderGradient", at = @At("HEAD"), cancellable = true)
    public void getThunderGradient(float delta, CallbackInfoReturnable<Float> cir) {
        if (UTools.CONFIG.clientWeatherClear) cir.setReturnValue(0F);

        cir.cancel();
    }
}