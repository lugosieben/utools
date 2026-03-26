package net.lugo.utools;

import net.fabricmc.api.ModInitializer;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.registration.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UTools implements ModInitializer {
	public static final String MOD_ID = "utools";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("UTools (" + MOD_ID + ") initializing.");

		ModConfig.HANDLER.load();
		KeyBindings.registerKeybinds();
		Commands.registerCommands();
		Events.registerEvents();

		LOGGER.info("UTools (" + MOD_ID + ") initialized.");
	}

	public static Logger getLogger() { return LOGGER; }
}