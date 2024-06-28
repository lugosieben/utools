package net.lugo.utools.registration;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.lugo.utools.features.LightOverlay;

public class Events {
    public static void registerEvents() {
        WorldRenderEvents.END.register(LightOverlay::renderEnd);
    }
}
