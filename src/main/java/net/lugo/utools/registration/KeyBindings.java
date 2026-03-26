package net.lugo.utools.registration;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.lugo.utools.UTools;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.features.*;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.parse(UTools.MOD_ID));
    private static final String BASE_KEY = "key." + UTools.MOD_ID;

    public static void registerKeybinds() {
        registerFullbrightGammaKey();
        registerZoomKey();
        registerZoomToggleKey();
        registerLightOverlayKey();
        registerAutoAttackKey();
        registerHidePlayersKey();
    }

    private static void registerFullbrightGammaKey() {
        KeyMapping fullbrightKey = new KeyMapping(BASE_KEY + ".fullbrightGammaToggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, CATEGORY);
        KeyBindingHelper.registerKeyBinding(fullbrightKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (fullbrightKey.consumeClick()) {
                if (ModConfig.useNightVisionInstead) {
                    FullBright.toggleNightVision();
                    FullBright.resetGamma();
                } else {
                    FullBright.toggleGamma();
                    FullBright.resetNV();
                }
            }
        });
    }

    private static boolean lastZoomKeyPressedState = false;
    private static void registerZoomKey() {
        KeyMapping zoomKey = new KeyMapping(BASE_KEY + ".zoom", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, CATEGORY);
        KeyBindingHelper.registerKeyBinding(zoomKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (zoomKey.isDown() && !lastZoomKeyPressedState) {
                lastZoomKeyPressedState = true;
                Zoom.on();
            } else if (!zoomKey.isDown() && lastZoomKeyPressedState) {
                lastZoomKeyPressedState = false;
                Zoom.off();
            }
        });
    }

    private static void registerZoomToggleKey() {
        KeyMapping zoomToggleKey = new KeyMapping(BASE_KEY + ".zoomToggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
        KeyBindingHelper.registerKeyBinding(zoomToggleKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (zoomToggleKey.consumeClick()) {
                if (Zoom.isZooming) {
                    Zoom.off();
                } else {
                    Zoom.on();
                }
            }
        });
    }

    private static void registerLightOverlayKey() {
        KeyMapping lightOverlayKey = new KeyMapping(BASE_KEY + ".lightOverlayToggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F9, CATEGORY);
        KeyBindingHelper.registerKeyBinding(lightOverlayKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (lightOverlayKey.consumeClick()) {
                LightOverlay.toggle();
            }
        });
    }

    private static void registerAutoAttackKey() {
        KeyMapping autoAttackKey = new KeyMapping(BASE_KEY + ".autoAttackToggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F10, CATEGORY);
        KeyBindingHelper.registerKeyBinding(autoAttackKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (autoAttackKey.consumeClick()) {
                AutoAttack.toggle();
            }
        });
    }

    private static void registerHidePlayersKey() {
        KeyMapping hidePlayersKey = new KeyMapping(BASE_KEY + ".hidePlayersToggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, CATEGORY);
        KeyBindingHelper.registerKeyBinding(hidePlayersKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (hidePlayersKey.consumeClick()) {
                HidePlayers.toggle();
            }
        });
    }
}
