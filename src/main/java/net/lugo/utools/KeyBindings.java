package net.lugo.utools;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.lugo.utools.features.FullBright;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    private static final String CATEGORY = "key.categories." + UTools.MOD_ID;
    private static final String BASE_KEY = "key." + UTools.MOD_ID;

    public static void registerKeybinds() {
        registerFullbrightKey();
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
}
