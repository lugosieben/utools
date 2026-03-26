package net.lugo.utools.features;

import net.lugo.utools.config.ModConfig;
import net.lugo.utools.util.Easing;
import net.minecraft.client.Minecraft;

public class Zoom {
    private static final float MIN_ZOOM = 1f;
    private static final float MAX_ZOOM = 100f;
    
    private static final Minecraft MC = Minecraft.getInstance();
    
    public static boolean isZooming = false;
    public static float goal = 1f;
    public static float lastGoal = 1f;
    public static float latestEffectiveZoom = 1f;
    public static float t = 0f;

    public static void on () {
        isZooming = true;
        lastGoal = latestEffectiveZoom;
        t = 0f;
        goal = Math.clamp(ModConfig.zoomMultiplier, MIN_ZOOM, MAX_ZOOM);
        if (ModConfig.zoomSmoothCam) MC.options.smoothCamera = true;
    }
    public static void off () {
        isZooming = false;
        lastGoal = latestEffectiveZoom;
        t = 0f;
        goal = 1f;
        if (ModConfig.zoomSmoothCam) MC.options.smoothCamera = false;
    }
    public static void increment (float amount) {
        lastGoal = latestEffectiveZoom;
        t = 0f;
        goal = Math.clamp(goal + amount, MIN_ZOOM, MAX_ZOOM);
    }
    public static void increment () {
        increment(ModConfig.scrollIncrement);
    }
    public static void decrement () {
        increment(-ModConfig.scrollIncrement);
    }

    public static Easing getZoomInEasing() {
        return ModConfig.zoomDuration == 0 ? Easing.INSTANT : ModConfig.zoomInEasing;
    }

    public static Easing getZoomOutEasing() {
        return ModConfig.zoomDuration == 0 ? Easing.INSTANT : ModConfig.zoomOutEasing;
    }
}
