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
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public class CopyScreenshotMixin {

    @Inject(
            method = "method_22691(Lnet/minecraft/client/texture/NativeImage;Ljava/io/File;Ljava/util/function/Consumer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/texture/NativeImage;writeTo(Ljava/io/File;)V",
                    shift = At.Shift.AFTER
            )
    )

    private static void afterScreenshot(NativeImage nativeImage, File file, Consumer<Text> consumer, CallbackInfo ci) {
        if (!UTools.getConfig().copyScreenshots) return;
        MinecraftClient MC = MinecraftClient.getInstance();
        new Thread(() -> {
            try {
                Image lastScreen = new ImageIcon(file.toString()).getImage();
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                CopyScreenshot.TransferableImage transferableImage = new CopyScreenshot.TransferableImage(lastScreen);
                clipboard.setContents(transferableImage, null);
                MC.execute(() -> HudMessage.show(Text.translatable("text.utools.message.copyScreenshot.success"), Formatting.DARK_AQUA));

            } catch (Exception e) {
                HudMessage.show(Text.translatable("text.utools.message.copyScreenshot.fail"), Formatting.RED);
                UTools.getLogger().error(e.toString());
            }
        }).start();

    }
}
