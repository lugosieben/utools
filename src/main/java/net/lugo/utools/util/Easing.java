package net.lugo.utools.util;

import org.apache.commons.lang3.function.TriFunction;

@SuppressWarnings("unused")
public enum Easing {
    LINEAR((Float start, Float end, Float x) -> {
        float difference = end - start;
        return start + (difference * x);
    }),
    
    INSTANT((Float start, Float end, Float x) -> end),
    
    EASE_OUT_EXPONENTIAL((Float start, Float end, Float x) -> {
        float ease = x >= 1 ? 1 : (float) (1 - Math.pow(2, -10 * x));
        return LINEAR.function.apply(start, end, ease);
    }),
    
    EASE_OUT_SINE((Float start, Float end, Float x) -> {
        float ease = (float) Math.sin((x * Math.PI) / 2);
        return LINEAR.function.apply(start, end, ease);
    }),
    
    EASE_OUT_BACK((Float start, Float end, Float x) -> {
        float c1 = 1.70158f;
        float c3 = c1 + 1;
        float ease = (float) (1 + c3 * Math.pow(x - 1, 3) + c1 * Math.pow(x - 1, 2));
        return LINEAR.function.apply(start, end, ease);
    });
    
    
    public final TriFunction<Float, Float, Float, Float> function;
    Easing(TriFunction<Float, Float, Float, Float> function) {
        this.function = function;
    }
    
}
