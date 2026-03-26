package net.lugo.utools.mixin.unpintexturepacks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.lugo.utools.config.ModConfig;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Pack.class)
public class UnpinTexturePacksMixin {
    @ModifyReturnValue(method = "isFixedPosition", at = @At("RETURN"))
    private boolean isPinned (boolean value) {
        if (ModConfig.unpinAllTexturePacks) return false;
        return value;
    }
}
