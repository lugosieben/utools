package net.lugo.utools;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UTools implements ModInitializer {
	public static final String MOD_ID = "utools";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("UTools (" + MOD_ID + ") initializing.");

		KeyBindings.registerKeybinds();
		Commands.registerCommands();

		LOGGER.info("UTools (" + MOD_ID + ") initialized.");
	}
}