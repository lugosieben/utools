package net.lugo.utools.features;

import com.mojang.blaze3d.platform.InputConstants;
import net.lugo.utools.util.HudMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class AutoAttack {
    private static boolean toggled = false;

    public static void toggle() {
        toggled = !toggled;
        MutableComponent message;
        if (toggled) message = Component.translatable("text.utools.message.autoAttackToggle.on");
        else message = Component.translatable("text.utools.message.autoAttackToggle.off");
        HudMessage.show(message, ChatFormatting.DARK_AQUA);
    }

    public static void clientTickEvent(Minecraft MC) {
        LocalPlayer player = MC.player;
        if (MC.level == null) { toggled = false; }

        if (toggled && player != null && player.isAlive() && player.getAttackStrengthScale(0) == 1F) {
            InputConstants.Key attackKey = InputConstants.getKey(MC.options.keyAttack.saveString());
            KeyMapping.click(attackKey);
        }

    }
}
