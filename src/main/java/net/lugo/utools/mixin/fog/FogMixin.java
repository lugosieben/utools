package net.lugo.utools.mixin.fog;

import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FogRenderer.class)
public class FogMixin {
    /*
    @Unique
    private static final ModConfig CONFIG = UTools.getConfig();

    @Inject(
        method = "applyFog(Lnet/minecraft/client/render/Camera;IZLnet/minecraft/client/render/RenderTickCounter;FLnet/minecraft/client/world/ClientWorld;)Lorg/joml/Vector4f;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/fog/FogModifier;shouldApply(Lnet/minecraft/block/enums/CameraSubmersionType;Lnet/minecraft/entity/Entity;)Z",
            shift = At.Shift.BEFORE)
    )
    private void applyFog(Camera camera, int viewDistance, boolean thick, RenderTickCounter tickCounter, float skyDarkness, ClientWorld world, CallbackInfoReturnable<Vector4f> cir) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        if(!(entity instanceof LivingEntity player)) return;

        boolean lava = cameraSubmersionType == CameraSubmersionType.LAVA;
        boolean powderSnow = cameraSubmersionType == CameraSubmersionType.POWDER_SNOW;
        boolean blindness = player.hasStatusEffect(StatusEffects.BLINDNESS);
        boolean darkness = player.hasStatusEffect(StatusEffects.DARKNESS);
        boolean water = cameraSubmersionType == CameraSubmersionType.WATER;
        boolean sky = false; //fogType == FogRenderer.FogType.WORLD;
        boolean terrain = !(lava || powderSnow || blindness || darkness || water || sky);

        boolean disableCurrentFog =
                (CONFIG.turnOffAllFogs)
                || (lava && !CONFIG.lavaFog)
                || (powderSnow && !CONFIG.powderSnowFog)
                || (blindness && !CONFIG.blindnessFog)
                || (darkness && !CONFIG.darknessFog)
                || (water && !CONFIG.waterFog)
                || (sky && !CONFIG.skyFog)
                || (terrain && !CONFIG.terrainFog);

        if (disableCurrentFog) {
            cir.cancel();
        }
    }
     */
}
