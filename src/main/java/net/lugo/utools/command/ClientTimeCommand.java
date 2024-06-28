package net.lugo.utools.command;

import com.mojang.brigadier.CommandDispatcher;
import net.lugo.utools.features.ClientTime;
import net.lugo.utools.features.ClientTime.ClientTimeType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ClientTimeCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess ignoredCommandRegistryAccess, CommandManager.RegistrationEnvironment ignoredRegistrationEnvironment) {
        dispatcher.register(CommandManager.literal("clienttime")
                .then(CommandManager.literal("reset")
                        .executes((ctx) -> { ClientTime.set(ClientTimeType.Disabled); return 1; }))
                .then(CommandManager.literal("set")
                        .then(CommandManager.literal("day")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.Day); return 1; }))
                        .then(CommandManager.literal("midnight")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.Midnight); return 1; }))
                        .then(CommandManager.literal("night")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.Night); return 1; }))
                        .then(CommandManager.literal("noon")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.Noon); return 1; }))
                )
        );
    }
}
