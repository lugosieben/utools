package net.lugo.utools.mixin.zoom;

import net.lugo.utools.config.ModConfig;
import net.lugo.utools.features.Zoom;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MouseHandler.class)
public class MouseSensitivityMixin {
    @ModifyVariable(method = "turnPlayer", at = @At("STORE"), ordinal = 3)
    private double updateMouse(double f) {
        if (ModConfig.zoomRelativeSensitivity) return f / (double) Zoom.latestEffectiveZoom;
        return f;
    }
}
