package net.lugo.utools.features;

import me.shedaniel.autoconfig.AutoConfig;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;

public class Zoom {
    private static final SimpleOption<Integer> fov = MinecraftClient.getInstance().options.getFov();
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    private static int originalFov = fov.getValue();

    public static void zoom (long multiplier) {
        int newvalue = (int)((long) fov.getValue() / multiplier );
        fov.setValue(newvalue);
    }

    public static void on () {
        originalFov = fov.getValue();
        if (config.zoomMultiplier == 0) return;
        zoom(config.zoomMultiplier);
    }

    public static void off() {
        fov.setValue(originalFov);
    }
}
