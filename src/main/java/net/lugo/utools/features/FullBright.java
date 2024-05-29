package net.lugo.utools.features;

import me.shedaniel.autoconfig.AutoConfig;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.util.HudMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FullBright {
    private static final SimpleOption<Double>  gamma = MinecraftClient.getInstance().options.getGamma();
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    public static int setValue(double value) {
        gamma.setValue(value);
        return 1;
    }

    public static int toggle() {
        double normalGamma = (double) config.normalGammaPercentage / 100;
        double fullGamma = (double) config.fullGammaPercentage / 100;

        double toPut = gamma.getValue() == fullGamma ? normalGamma : fullGamma;
        int percentage = (int)toPut * 100;
        MutableText message = Text.translatable("text.utools.message.gammaPercentage", String.valueOf(percentage));
        gamma.setValue(toPut);
        HudMessage.show(message, Formatting.DARK_AQUA);
        return 1;
    }
}
