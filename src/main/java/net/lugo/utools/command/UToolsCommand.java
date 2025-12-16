package net.lugo.utools.command;

import com.mojang.brigadier.CommandDispatcher;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;

public class UToolsCommand {
    final static MinecraftClient MC = MinecraftClient.getInstance();

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess ignoredCommandRegistryAccess) {
        dispatcher.register(ClientCommandManager.literal("utools")
            .then(ClientCommandManager.literal("config")
                .executes(context -> {
                    MC.send(() -> MC.setScreen(AutoConfigClient.getConfigScreen(ModConfig.class, MC.currentScreen).get()));
                        return 1;
                    })
                )
        );
    }
}
