package net.lugo.utools.mixin.copyscreenshot;

import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.features.CopyScreenshot;
import net.lugo.utools.util.HudMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import com.mojang.blaze3d.platform.NativeImage;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.util.function.Consumer;

@Mixin(Screenshot.class)
public class CopyScreenshotMixin {

    @Inject(
            method = "method_22691(Lcom/mojang/blaze3d/platform/NativeImage;Ljava/io/File;Ljava/util/function/Consumer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/platform/NativeImage;writeToFile(Ljava/io/File;)V",
                    shift = At.Shift.AFTER
            )
    )

    private static void afterScreenshot(NativeImage nativeImage, File file, Consumer<Component> consumer, CallbackInfo ci) {
        if (!ModConfig.copyScreenshots) return;
        Minecraft MC = Minecraft.getInstance();
        new Thread(() -> {
            try {
                Image lastScreen = new ImageIcon(file.toString()).getImage();
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                CopyScreenshot.TransferableImage transferableImage = new CopyScreenshot.TransferableImage(lastScreen);
                clipboard.setContents(transferableImage, null);
                MC.execute(() -> HudMessage.show(Component.translatable("text.utools.message.copyScreenshot.success"), ChatFormatting.DARK_AQUA));

            } catch (Exception e) {
                HudMessage.show(Component.translatable("text.utools.message.copyScreenshot.fail"), ChatFormatting.RED);
                UTools.getLogger().error(e.toString());
            }
        }).start();

    }
}
