package net.lugo.utools.mixin.copyscreenshot;

import net.lugo.utools.UTools;
import net.lugo.utools.features.CopyScreenshot;
import net.lugo.utools.util.HudMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public class CopyScreenshotMixin {
    @Inject(method = "method_1661", at = @At("TAIL"))
    private static void onScreenshot(NativeImage image, File file, Consumer<Text> textReceiver, CallbackInfo ci) {
        if (!UTools.getConfig().copyScreenshots) return;
        MinecraftClient MC = MinecraftClient.getInstance();
        try {
            File screenPath = new File(MC.runDirectory.getAbsolutePath(), "screenshots");
            Optional<Path> lastScreenPath = Files.list(screenPath.toPath())
                    .filter(f -> !Files.isDirectory(f))
                    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
            if (lastScreenPath.isEmpty()) return;
            Image lastScreen = new ImageIcon(lastScreenPath.get().toString()).getImage();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            CopyScreenshot.TransferableImage transferableImage = new CopyScreenshot.TransferableImage(lastScreen);
            clipboard.setContents(transferableImage, null);

            HudMessage.show(Text.translatable("text.utools.message.copyScreenshot.success"), Formatting.DARK_AQUA);
        } catch (IOException e) {
            HudMessage.show(Text.translatable("text.utools.message.copyScreenshot.fail"), Formatting.RED);
            UTools.getLogger().error(e.toString());
        }
    }
}
