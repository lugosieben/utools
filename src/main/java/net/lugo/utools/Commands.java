package net.lugo.utools;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.lugo.utools.command.FullBrightCommand;
import net.lugo.utools.command.UToolsCommand;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Commands {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(FullBrightCommand::register);
        CommandRegistrationCallback.EVENT.register(UToolsCommand::register);
    }
}
