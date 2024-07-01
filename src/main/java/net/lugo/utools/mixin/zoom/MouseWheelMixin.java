package net.lugo.utools.mixin.zoom;

import net.lugo.utools.features.Zoom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseWheelMixin {
    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (Zoom.isZooming) {
            ci.cancel();

            boolean isDiscreteScroll = MinecraftClient.getInstance().options.getDiscreteMouseScroll().getValue();
            double sensitivity = MinecraftClient.getInstance().options.getMouseWheelSensitivity().getValue();
            double scrollY = (isDiscreteScroll ? Math.signum(vertical) : vertical) * sensitivity;

            if (scrollY < 0) Zoom.increment();
            if (scrollY > 0) Zoom.decrement();
        }
    }
}
