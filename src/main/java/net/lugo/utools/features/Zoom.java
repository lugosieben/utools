package net.lugo.utools.features;

import net.lugo.utools.UTools;
import net.minecraft.client.MinecraftClient;

public class Zoom {
    private static final float MIN_ZOOM = 1f;
    private static final float MAX_ZOOM = 100f;
    
    private static final MinecraftClient MC = MinecraftClient.getInstance();
    
    public static boolean isZooming = false;
    public static float goal = 1f;
    public static float lastGoal = 1f;
    public static float latestEffectiveZoom = 1f;
    public static float t = 0f;

    public static void on () {
        isZooming = true;
        lastGoal = latestEffectiveZoom;
        t = 0f;
        goal = Math.clamp(UTools.getConfig().zoomMultiplier, MIN_ZOOM, MAX_ZOOM);
        if (UTools.getConfig().zoomSmoothCam) MC.options.smoothCameraEnabled = true;
    }
    public static void off () {
        isZooming = false;
        lastGoal = latestEffectiveZoom;
        t = 0f;
        goal = 1f;
        if (UTools.getConfig().zoomSmoothCam) MC.options.smoothCameraEnabled = false;
    }
    public static void increment (float amount) {
        lastGoal = latestEffectiveZoom;
        t = 0f;
        goal = Math.clamp(goal + amount, MIN_ZOOM, MAX_ZOOM);
    }
    public static void increment () {
        increment(UTools.getConfig().scrollIncrement);
    }
    public static void decrement () {
        increment(-UTools.getConfig().scrollIncrement);
    }
}
