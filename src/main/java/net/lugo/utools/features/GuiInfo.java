package net.lugo.utools.features;

import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GuiInfo {
    private static final ModConfig CONFIG = UTools.getConfig();
    private static final MinecraftClient MC = MinecraftClient.getInstance();
    private static final TextRenderer textRenderer = MC.textRenderer;

    public static void hudRenderCallback(DrawContext drawContext, float ignoredRenderTickCounter) {
        if (MC == null || MC.player == null) return;
        MutableText renderString = Text.empty();

        if (CONFIG.timeDisplay) {
            LocalTime currentTime = LocalTime.now();
            String timeString = currentTime.format(DateTimeFormatter.ofPattern("H:mm:ss"));
            renderString = Text.translatable("text.utools.info.timeInfo", timeString);
        }
        if (CONFIG.fpsDisplay) {
            String fpsString = String.valueOf(MC.getCurrentFps());
            renderString = appendWithSeparator(renderString, Text.translatable("text.utools.info.fpsInfo", fpsString));
        }
        if (CONFIG.pingDisplay) {
            PlayerListEntry playerListEntry = MC.player.networkHandler.getPlayerListEntry(MC.player.getUuid());
            if (playerListEntry == null) return;
            String pingString = String.valueOf(playerListEntry.getLatency());
            renderString = appendWithSeparator(renderString, Text.translatable("text.utools.info.pingInfo", pingString));
        }
        if (CONFIG.posDisplay) {
            BlockPos pos = MC.player.getBlockPos();
            String posString = pos.toShortString();
            renderString = appendWithSeparator(renderString, Text.translatable("text.utools.info.posInfo", posString));
        }
        if (CONFIG.compassDisplay) {
            String compassString = MC.player.getHorizontalFacing().toString();
            renderString = appendWithSeparator(renderString, Text.translatable("text.utools.info.compassInfo", compassString));
        }

        drawContext.drawText(textRenderer, renderString, 3, 3, 0xFFFFFF, true);
    }

    private static MutableText appendWithSeparator(MutableText original, MutableText toAppend) {
        return original.equals(Text.empty()) ? toAppend : original.append(" / ").append(toAppend);
    }
}
