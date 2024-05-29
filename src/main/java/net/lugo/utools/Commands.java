package net.lugo.utools;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.lugo.utools.command.FullBrightCommand;
import net.lugo.utools.command.UToolsCommand;

public class Commands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(FullBrightCommand::register);
        CommandRegistrationCallback.EVENT.register(UToolsCommand::register);
    }
}
