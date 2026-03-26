package net.lugo.utools.features;

import net.lugo.utools.config.ModConfig;
import net.lugo.utools.util.HudMessage;
import net.lugo.utools.util.PlayerEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffects;
import org.jetbrains.annotations.NotNull;

public class FullBright {
    private static final OptionInstance<@NotNull Double>  gamma = Minecraft.getInstance().options.gamma();

    public static int setValue(double value) {
        gamma.set(value);
        return 1;
    }

    public static int toggleGamma() {
        double normalGamma = (double) ModConfig.normalGammaPercentage / 100;
        double fullGamma = (double) ModConfig.fullGammaPercentage / 100;

        double toPut = gamma.get() == fullGamma ? normalGamma : fullGamma;
        int percentage = (int)toPut * 100;
        MutableComponent message = Component.translatable("text.utools.message.gammaPercentage", String.valueOf(percentage));
        gamma.set(toPut);
        HudMessage.show(message, ChatFormatting.DARK_AQUA);
        return 1;
    }

    public static void toggleNightVision() {
        Minecraft MC = Minecraft.getInstance();
        if (MC.player == null) return;
        boolean hasStatusEffect = MC.player.hasEffect(MobEffects.NIGHT_VISION);
        if (!hasStatusEffect) {
            PlayerEffects.addPermanentEffect(MC.player, MobEffects.NIGHT_VISION);
            MutableComponent message = Component.translatable("text.utools.message.nightVision", "on");
            HudMessage.show(message, ChatFormatting.GREEN);
        } else {
            MC.player.removeEffect(MobEffects.NIGHT_VISION);
            MutableComponent message = Component.translatable("text.utools.message.nightVision", "off");
            HudMessage.show(message, ChatFormatting.RED);
        }
    }

    public static void resetGamma() {
        if (gamma.get() > 1) gamma.set((double) ModConfig.normalGammaPercentage / 100);
    }

    public static void resetNV() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null || !player.hasEffect(MobEffects.NIGHT_VISION)) return;
        player.removeEffect(MobEffects.NIGHT_VISION);
    }
}
