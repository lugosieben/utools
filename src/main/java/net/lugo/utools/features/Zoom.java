package net.lugo.utools.features;

public class Zoom {
    private static boolean zooming = false;

    public static void on () {
        zooming = true;
    }

    public static void off() {
        zooming = false;
    }

    public static boolean isZooming () {
        return zooming;
    }
}
