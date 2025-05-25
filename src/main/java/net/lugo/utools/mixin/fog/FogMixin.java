package net.lugo.utools.mixin.fog;

import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackgroundRenderer.class)
public class FogMixin {
    @Unique
    private static final ModConfig CONFIG = UTools.getConfig();

    @Inject(method = "applyFog", at = @At("RETURN"), cancellable = true)
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<Fog> cir) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        if(!(entity instanceof LivingEntity player)) return;

        boolean lava = cameraSubmersionType == CameraSubmersionType.LAVA;
        boolean powderSnow = cameraSubmersionType == CameraSubmersionType.POWDER_SNOW;
        boolean blindness = player.hasStatusEffect(StatusEffects.BLINDNESS);
        boolean darkness = player.hasStatusEffect(StatusEffects.DARKNESS);
        boolean water = cameraSubmersionType == CameraSubmersionType.WATER;
        boolean sky = fogType == BackgroundRenderer.FogType.FOG_SKY;
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
            cir.setReturnValue(new Fog(-8F, 1000000F, FogShape.CYLINDER, 1, 1, 1,1));
        }
    }
}
