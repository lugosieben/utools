package net.lugo.utools.features;

import net.lugo.utools.UTools;
import net.lugo.utools.util.HudMessage;
import net.lugo.utools.util.PlayerEffects;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class FullBright {
    private static final SimpleOption<Double>  gamma = MinecraftClient.getInstance().options.getGamma();

    public static int setValue(double value) {
        gamma.setValue(value);
        return 1;
    }

    public static int toggleGamma() {
        double normalGamma = (double) UTools.getConfig().normalGammaPercentage / 100;
        double fullGamma = (double) UTools.getConfig().fullGammaPercentage / 100;

        double toPut = gamma.getValue() == fullGamma ? normalGamma : fullGamma;
        int percentage = (int)toPut * 100;
        MutableText message = Text.translatable("text.utools.message.gammaPercentage", String.valueOf(percentage));
        gamma.setValue(toPut);
        HudMessage.show(message, Formatting.DARK_AQUA);
        return 1;
    }

    public static void toggleNightVision() {
        MinecraftClient MC = MinecraftClient.getInstance();
        if (MC.player == null) return;
        boolean hasStatusEffect = MC.player.hasStatusEffect(StatusEffects.NIGHT_VISION);
        if (!hasStatusEffect) {
            PlayerEffects.addPermanentEffect(MC.player, StatusEffects.NIGHT_VISION);
            MutableText message = Text.translatable("text.utools.message.nightVision", "on");
            HudMessage.show(message, Formatting.GREEN);
        } else {
            MC.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            MutableText message = Text.translatable("text.utools.message.nightVision", "off");
            HudMessage.show(message, Formatting.RED);
        }
    }
}
