package net.lugo.utools.features;

import net.lugo.utools.UTools;
import net.minecraft.client.MinecraftClient;

public class Zoom {
    private static final double MIN_ZOOM = 0.1D;
    private static final double MAX_ZOOM = 100D;

    public static boolean isZooming = false;
    public static double goal = 1D;
    private static final MinecraftClient MC = MinecraftClient.getInstance();

    public static void on () {
        isZooming = true;
        goal = Math.clamp(UTools.getConfig().zoomMultiplier, MIN_ZOOM, MAX_ZOOM);
        MC.options.smoothCameraEnabled = true;
    }
    public static void off () {
        isZooming = false;
        goal = 1D;
        MC.options.smoothCameraEnabled = false;
    }
    public static void increment (Double amount) {
        goal = Math.clamp(goal + amount, MIN_ZOOM, MAX_ZOOM);
    }
    public static void increment () {
        increment(UTools.getConfig().scrollIncrement);
    }
    public static void decrement () {
        increment(-UTools.getConfig().scrollIncrement);
    }
}
