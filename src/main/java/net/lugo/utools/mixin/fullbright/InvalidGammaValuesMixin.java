package net.lugo.utools.mixin.fullbright;

/* CODE FROM  https://github.com/Sjouwer/gamma-utils/blob/1.20.5/src/main/java/io/github/sjouwer/gammautils/mixin/MixinSimpleOption.java */

import com.mojang.serialization.Codec;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionInstance.class)
public class InvalidGammaValuesMixin<T> {

    @Shadow
    @Final
    Component caption;

    @Shadow
    private T value;

    /**
     * Mixin to allow saving "invalid" gamma values into the options file
     */
    @Inject(method = "codec", at = @At("HEAD"), cancellable = true)
    private void returnFakeCodec(CallbackInfoReturnable<Codec<Double>> info) {
        if (caption.getString().equals(I18n.get("options.gamma"))) {
            info.setReturnValue(Codec.DOUBLE);
        }
    }

    /**
     * Mixin to allow setting "invalid" gamma values
     */
    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    private void setRealValue(T value, CallbackInfo info) {
        if (caption.getString().equals(I18n.get("options.gamma"))) {
            this.value = value;
            info.cancel();
        }
    }
}