package net.lugo.utools.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.lugo.utools.config.ModConfig;
import net.lugo.utools.features.ClientTime;
import net.lugo.utools.features.ClientTime.ClientTimeType;
import net.minecraft.commands.CommandBuildContext;

public class ClientTimeCommand {
    private static int setCustomTime(CommandContext<FabricClientCommandSource> context) {
        ClientTime.set(ClientTimeType.CUSTOM);
        ModConfig.customClientTime = IntegerArgumentType.getInteger(context, "time");
        return 1;
    }

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext ignoredCommandRegistryAccess) {
        dispatcher.register(ClientCommands.literal("clienttime")
                .then(ClientCommands.literal("reset")
                        .executes((ctx) -> { ClientTime.set(ClientTimeType.DISABLED); return 1; }))
                .then(ClientCommands.literal("set")
                        .then(ClientCommands.literal("day")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.DAY); return 1; }))
                        .then(ClientCommands.literal("midnight")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.MIDNIGHT); return 1; }))
                        .then(ClientCommands.literal("night")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.NIGHT); return 1; }))
                        .then(ClientCommands.literal("noon")
                                .executes((ctx) -> { ClientTime.set(ClientTimeType.DAY); return 1; }))
                        .then(ClientCommands.argument("time", IntegerArgumentType.integer())
                                .executes(ClientTimeCommand::setCustomTime))));
    }
}