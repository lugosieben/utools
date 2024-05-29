package net.lugo.utools.command;

import com.mojang.brigadier.CommandDispatcher;
import me.shedaniel.autoconfig.AutoConfig;
import net.lugo.utools.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class UToolsCommand {
    final static MinecraftClient MC = MinecraftClient.getInstance();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess ignoredCommandRegistryAccess, CommandManager.RegistrationEnvironment ignoredRegistrationEnvironment) {
        dispatcher.register(CommandManager.literal("utools")
                .then(CommandManager.literal("config")
                        .executes(context -> {
                            MC.execute(() -> MC.setScreen(AutoConfig.getConfigScreen(ModConfig.class, MC.currentScreen).get()));
                            return 1;
                        })
                )
        );
    }
}
