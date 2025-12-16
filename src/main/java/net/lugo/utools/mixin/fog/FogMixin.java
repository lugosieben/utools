package net.lugo.utools.mixin.fog;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
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
    private static List<FogEnvironment> FOG_ENVIRONMENTS;
    @Unique
    private static final ModConfig CONFIG = UTools.getConfig();

    @WrapOperation(
            method = "setupFog(Lnet/minecraft/client/Camera;ILnet/minecraft/client/DeltaTracker;FLnet/minecraft/client/multiplayer/ClientLevel;)Lorg/joml/Vector4f;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/fog/environment/FogEnvironment;setupFog(Lnet/minecraft/client/renderer/fog/FogData;Lnet/minecraft/client/Camera;Lnet/minecraft/client/multiplayer/ClientLevel;FLnet/minecraft/client/DeltaTracker;)V"))
    private void applyFog(FogEnvironment fogModifier, FogData fogData, Camera camera, ClientLevel clientWorld, float v, DeltaTracker renderTickCounter, Operation<Void> original) {

        v /= 2;
        boolean lava = fogModifier.equals(FOG_ENVIRONMENTS.getFirst());
        boolean powderSnow = fogModifier.equals(FOG_ENVIRONMENTS.get(1));
        boolean blindness = fogModifier.equals(FOG_ENVIRONMENTS.get(2));
        boolean darkness = fogModifier.equals(FOG_ENVIRONMENTS.get(3));
        boolean water = fogModifier.equals(FOG_ENVIRONMENTS.get(4));
        boolean terrain = fogModifier.equals(FOG_ENVIRONMENTS.get(5));

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

    @ModifyConstant(method = "setupFog(Lnet/minecraft/client/Camera;ILnet/minecraft/client/DeltaTracker;FLnet/minecraft/client/multiplayer/ClientLevel;)Lorg/joml/Vector4f;", constant = @Constant(intValue = 16))
    private int applyFog(int value) {
        if (!CONFIG.renderDistanceFog) {
            value *= 2;
        }

        return value;
    }
}
