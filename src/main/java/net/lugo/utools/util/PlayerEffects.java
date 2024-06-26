package net.lugo.utools.util;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

public class PlayerEffects {
    public static void addPermanentEffect(ClientPlayerEntity player, RegistryEntry<StatusEffect> effect) {
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(effect, -1);
        player.addStatusEffect(statusEffectInstance);
    }
}
