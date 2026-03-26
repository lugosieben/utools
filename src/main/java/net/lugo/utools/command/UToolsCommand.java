package net.lugo.utools.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;

public class UToolsCommand {
    final static Minecraft MC = Minecraft.getInstance();

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext ignoredCommandRegistryAccess) {
        dispatcher.register(ClientCommands.literal("utools")
            .then(ClientCommands.literal("config")
                .executes(context -> {
                    MC.schedule(() -> MC.setScreen(ModConfig.makeScreen(MC.screen)));
                        return 1;
                    })
                )
        );
    }
}
