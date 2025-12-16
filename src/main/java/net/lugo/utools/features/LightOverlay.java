package net.lugo.utools.features;

import me.shedaniel.autoconfig.AutoConfig;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.util.HudMessage;
import net.lugo.utools.util.OverlayRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import java.awt.*;

public class LightOverlay {
    private static boolean activated = false;
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    static Minecraft MC = Minecraft.getInstance();

    public static void toggle() {
        activated = !activated;
        MutableComponent message = Component.translatable("text.utools.message.lightOverlayToggle.on");
        if (!activated) message = Component.translatable("text.utools.message.lightOverlayToggle.off");
        HudMessage.show(message, ChatFormatting.DARK_AQUA);
    }

    public static void renderEnd() {
        if (MC.player == null || MC.level == null || MC.isPaused() || !activated) return;
        Vec3 playerPos = new Vec3(MC.player.getX(), MC.player.getY(), MC.player.getZ());

        OverlayRenderer.startBatch();

        for (int x = -20; x <= 20; x++) {
            for (int y = -10; y <= 3; y++) {
                for (int z = -20; z <= 20; z++) {
                    Vec3 relativePos = new Vec3(x,y,z);
                    Vec3 pos = playerPos.add(relativePos);
                    BlockPos blockPos = new BlockPos((int)pos.x, (int)pos.y, (int)pos.z);
                    if (MC.level.loadedAndEntityCanStandOn(blockPos, MC.player) && !MC.level.loadedAndEntityCanStandOn(blockPos.above(), MC.player)) {
                        Color color = Color.RED;
                        int blockLightLevel = MC.level.getBrightness(LightLayer.BLOCK, blockPos.above());
                        if (blockLightLevel >= config.lightOverlayThreshold) color = Color.GREEN;
                        OverlayRenderer.addBlock(MC.gameRenderer.getMainCamera(), Vec3.atLowerCornerOf(blockPos), color.getRed(), color.getGreen(), color.getBlue(), 0.01F);
                    }
                }
            }
        }

        OverlayRenderer.endBatch();
    }
}
