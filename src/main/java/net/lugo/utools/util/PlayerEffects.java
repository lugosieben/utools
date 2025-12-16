package net.lugo.utools.util;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.NotNull;

public class PlayerEffects {
    public static void addPermanentEffect(LocalPlayer player, Holder<@NotNull MobEffect> effect) {
        MobEffectInstance statusEffectInstance = new MobEffectInstance(effect, -1);
        player.addEffect(statusEffectInstance);
    }
}
