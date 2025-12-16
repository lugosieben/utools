package net.lugo.utools.mixin.clienttimeweather;

import net.lugo.utools.UTools;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class ClientWeatherMixin {

    @Inject(method = "getRainLevel", at = @At("HEAD"), cancellable = true)
    public void getRainGradient(float delta, CallbackInfoReturnable<Float> cir) {
        if (UTools.getConfig().clientWeatherClear) {
            cir.setReturnValue(0F);
        }
    }

    @Inject(method = "getThunderLevel", at = @At("HEAD"), cancellable = true)
    public void getThunderGradient(float delta, CallbackInfoReturnable<Float> cir) {
        if (UTools.getConfig().clientWeatherClear) {
            cir.setReturnValue(0F);
        }
    }
}