package net.lugo.utools.registration;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.lugo.utools.features.AutoAttack;

public class Events {
    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(AutoAttack::clientTickEvent);
    }
}
