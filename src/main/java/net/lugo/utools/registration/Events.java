package net.lugo.utools.registration;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.lugo.utools.features.AutoAttack;
import net.lugo.utools.features.LightOverlay;

public class Events {
    public static void registerEvents() {
        WorldRenderEvents.END.register(LightOverlay::renderEnd);
        ClientTickEvents.END_CLIENT_TICK.register(AutoAttack::clientTickEvent);
    }
}
