package net.lugo.utools;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.lugo.utools.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UTools implements ModInitializer {
	public static final String MOD_ID = "utools";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("UTools (" + MOD_ID + ") initializing.");

		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		KeyBindings.registerKeybinds();
		Commands.registerCommands();
		Events.registerEvents();

		LOGGER.info("UTools (" + MOD_ID + ") initialized.");
	}
}