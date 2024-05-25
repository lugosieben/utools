package net.lugo.utools;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.lugo.utools.command.FullBrightCommand;

public class Commands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(FullBrightCommand::register);
    }
}
