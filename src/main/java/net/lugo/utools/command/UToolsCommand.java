package net.lugo.utools.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;

public class UToolsCommand {
    final static Minecraft MC = Minecraft.getInstance();

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext ignoredCommandRegistryAccess) {
        dispatcher.register(ClientCommandManager.literal("utools")
            .then(ClientCommandManager.literal("config")
                .executes(context -> {
                    MC.schedule(() -> MC.setScreen(ModConfig.makeScreen(MC.screen)));
                        return 1;
                    })
                )
        );
    }
}
