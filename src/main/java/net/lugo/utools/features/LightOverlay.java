package net.lugo.utools.features;

import me.shedaniel.autoconfig.AutoConfig;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.util.HudMessage;
import net.lugo.utools.util.OverlayRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;

import java.awt.*;

public class LightOverlay {
    private static boolean activated = false;
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    static MinecraftClient MC = MinecraftClient.getInstance();

    public static void toggle() {
        activated = !activated;
        MutableText message = Text.translatable("text.utools.message.lightOverlayToggle.on");
        if (!activated) message = Text.translatable("text.utools.message.lightOverlayToggle.off");
        HudMessage.show(message, Formatting.DARK_AQUA);
    }

    public static void renderEnd() {
        if (MC.player == null || MC.world == null || MC.isPaused() || !activated) return;
        Vec3d playerPos = new Vec3d(MC.player.getX(), MC.player.getY(), MC.player.getZ());

        OverlayRenderer.startBatch();

        for (int x = -20; x <= 20; x++) {
            for (int y = -10; y <= 3; y++) {
                for (int z = -20; z <= 20; z++) {
                    Vec3d relativePos = new Vec3d(x,y,z);
                    Vec3d pos = playerPos.add(relativePos);
                    BlockPos blockPos = new BlockPos((int)pos.x, (int)pos.y, (int)pos.z);
                    if (MC.world.isTopSolid(blockPos, MC.player) && !MC.world.isTopSolid(blockPos.up(), MC.player)) {
                        Color color = Color.RED;
                        int blockLightLevel = MC.world.getLightLevel(LightType.BLOCK, blockPos.up());
                        if (blockLightLevel >= config.lightOverlayThreshold) color = Color.GREEN;
                        OverlayRenderer.addBlock(MC.gameRenderer.getCamera(), Vec3d.of(blockPos), color.getRed(), color.getGreen(), color.getBlue(), 0.01F);
                    }
                }
            }
        }

        OverlayRenderer.endBatch();
    }
}
