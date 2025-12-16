package net.lugo.utools.features;

import net.lugo.utools.util.HudMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class HidePlayers {
    private static boolean toggled = false;

    public static boolean get() {
        return toggled;
    }

    public static void toggle() {
        toggled = !toggled;

        MutableComponent message;
        if (toggled) message = Component.translatable("text.utools.message.hidePlayersToggle.on");
        else message = Component.translatable("text.utools.message.hidePlayersToggle.off");
        HudMessage.show(message, ChatFormatting.DARK_AQUA);
    }
}
