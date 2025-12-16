package net.lugo.utools.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;

public class HudMessage {
    private static final Minecraft client = Minecraft.getInstance();

    public static void show(MutableComponent string, ChatFormatting format) {
        MutableComponent message = string.withStyle(format);
        client.gui.setOverlayMessage(message, false);
    }
}
