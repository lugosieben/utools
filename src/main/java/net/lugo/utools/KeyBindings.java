package net.lugo.utools;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.lugo.utools.features.FullBright;
import net.lugo.utools.features.LightOverlay;
import net.lugo.utools.features.Zoom;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    private static final String CATEGORY = "key.categories." + UTools.MOD_ID;
    private static final String BASE_KEY = "key." + UTools.MOD_ID;

    public static void registerKeybinds() {
        registerFullbrightKey();
        registerZoomKey();
        registerLightOverlayKey();
    }

    private static void registerFullbrightKey() {
        KeyBinding fullbrightKey = new KeyBinding(BASE_KEY + ".fullbrightToggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY);
        KeyBindingHelper.registerKeyBinding(fullbrightKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (fullbrightKey.wasPressed()) {
                FullBright.toggle();
            }
        });
    }

    private static boolean lastZoomKeyPressedState = false;
    private static void registerZoomKey() {
        KeyBinding zoomKey = new KeyBinding(BASE_KEY + ".zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, CATEGORY);
        KeyBindingHelper.registerKeyBinding(zoomKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (zoomKey.isPressed() && !lastZoomKeyPressedState) {
                lastZoomKeyPressedState = true;
                Zoom.on();
            } else if (!zoomKey.isPressed() && lastZoomKeyPressedState) {
                lastZoomKeyPressedState = false;
                Zoom.off();
            }
        });
    }

    private static void registerLightOverlayKey() {
        KeyBinding lightOverlayKey = new KeyBinding(BASE_KEY + ".lightOverlayToggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F9, CATEGORY);
        KeyBindingHelper.registerKeyBinding(lightOverlayKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (lightOverlayKey.wasPressed()) {
                LightOverlay.toggle();
            }
        });
    }
}
