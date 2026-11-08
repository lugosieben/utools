package net.lugo.utools.mixin.visualmods;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerItemInHandLayer.class)
public class LowShieldMixin {
    @Inject(method = "submitArmWithItem(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/HumanoidArm;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V", at = @At("HEAD"))
    public void submitArmWithItem(AvatarRenderState state, ItemStackRenderState itemState, ItemStack stack, HumanoidArm arm, PoseStack matrices, SubmitNodeCollector queue, int light, CallbackInfo ci) {
        if (stack.getItem() == Items.SHIELD && arm == HumanoidArm.RIGHT) {
            matrices.translate(0, (double) ModConfig.lowShieldModifier / 100, 0);
        }
    }
}
