package net.lugo.utools.features;

import net.lugo.utools.util.HudMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AutoAttack {
    private static boolean toggled = false;

    public static void toggle() {
        toggled = !toggled;
        MutableText message;
        if (toggled) message = Text.translatable("text.utools.message.autoAttackToggle.on");
        else message = Text.translatable("text.utools.message.autoAttackToggle.off");
        HudMessage.show(message, Formatting.DARK_AQUA);
    }

    public static void clientTickEvent(MinecraftClient MC) {
        ClientPlayerEntity player = MC.player;
        if (MC.world == null) { toggled = false; }

        if (toggled && player != null && player.isAlive() && player.getAttackCooldownProgress(0) == 1F) {
            InputUtil.Key attackKey = InputUtil.fromTranslationKey(MC.options.attackKey.getBoundKeyTranslationKey());
            KeyBinding.onKeyPressed(attackKey);
        }

    }
}
