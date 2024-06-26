package net.lugo.utools.mixin.unpintexturepacks;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.lugo.utools.UTools;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ResourcePackProfile.class)
public class UnpinTexturePacksMixin {
    @ModifyReturnValue(method = "isPinned", at = @At("RETURN"))
    private boolean isPinned (boolean value) {
        if (UTools.getConfig().unpinAllTexturePacks) return false;
        return value;
    }
}
