package net.lugo.utools.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class HudMessage {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    public static void show(MutableText string, Formatting format) {
        MutableText message = string.formatted(format);
        client.inGameHud.setOverlayMessage(message, false);
    }
}
