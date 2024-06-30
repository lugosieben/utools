package net.lugo.utools.util;

public class Easings {
    public static float linear(float start, float end, float progress) {
        float difference = end - start;
        return start + (difference * progress);
    }
    
    public static float easeOutExpo(float start, float end, float progress) {
        float ease = progress >= 1 ? 1 : (float) (1 - Math.pow(2, -10 * progress));
        return linear(start, end, ease);
    }
}
