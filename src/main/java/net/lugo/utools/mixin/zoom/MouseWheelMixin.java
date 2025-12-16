package net.lugo.utools.mixin.zoom;

import net.lugo.utools.features.Zoom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseWheelMixin {
    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (Zoom.isZooming) {
            ci.cancel();

            boolean isDiscreteScroll = Minecraft.getInstance().options.discreteMouseScroll().get();
            double sensitivity = Minecraft.getInstance().options.mouseWheelSensitivity().get();
            double scrollY = (isDiscreteScroll ? Math.signum(vertical) : vertical) * sensitivity;

            if (scrollY < 0) Zoom.decrement();
            if (scrollY > 0) Zoom.increment();
        }
    }
}
