package net.lugo.utools.mixin.fog;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.render.fog.FogModifier;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.List;

@Mixin(FogRenderer.class)
public class FogMixin {
    @Shadow
    @Final
    private static List<FogModifier> FOG_MODIFIERS;
    @Unique
    private static final ModConfig CONFIG = UTools.getConfig();

    @WrapOperation(
            method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/fog/FogModifier;applyStartEndModifier(Lnet/minecraft/client/render/fog/FogData;Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/world/ClientWorld;FLnet/minecraft/client/render/RenderTickCounter;)V"))
    private void applyFog(FogModifier fogModifier, FogData fogData, Camera camera, ClientWorld clientWorld, float v, RenderTickCounter renderTickCounter, Operation<Void> original) {

        v /= 2;
        boolean lava = fogModifier.equals(FOG_MODIFIERS.getFirst());
        boolean powderSnow = fogModifier.equals(FOG_MODIFIERS.get(1));
        boolean blindness = fogModifier.equals(FOG_MODIFIERS.get(2));
        boolean darkness = fogModifier.equals(FOG_MODIFIERS.get(3));
        boolean water = fogModifier.equals(FOG_MODIFIERS.get(4));
        boolean terrain = fogModifier.equals(FOG_MODIFIERS.get(5));

        boolean disableCurrentFog =
                   CONFIG.turnOffAllFogs
                || (lava && !CONFIG.lavaFog)
                || (powderSnow && !CONFIG.powderSnowFog)
                || (blindness && !CONFIG.blindnessFog)
                || (darkness && !CONFIG.darknessFog)
                || (water && !CONFIG.waterFog)
                || (terrain && !CONFIG.terrainFog);

        if (disableCurrentFog) {
            fogData.environmentalStart = Float.MAX_VALUE;
            fogData.environmentalEnd = Float.MAX_VALUE;
            fogData.skyEnd = Float.MAX_VALUE;
            fogData.cloudEnd = Float.MAX_VALUE;
        } else {
            original.call(fogModifier, fogData, camera, clientWorld, v, renderTickCounter);
        }
    }

    @ModifyConstant(method = "applyFog(Lnet/minecraft/client/render/Camera;ILnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;", constant = @Constant(intValue = 16))
    private int applyFog(int value) {
        if (!CONFIG.renderDistanceFog) {
            value *= 2;
        }

        return value;
    }
}
