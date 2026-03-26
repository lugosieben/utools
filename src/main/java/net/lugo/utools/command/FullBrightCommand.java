package net.lugo.utools.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.lugo.utools.features.FullBright;
import net.minecraft.commands.CommandBuildContext;
import com.mojang.brigadier.CommandDispatcher;

import static com.mojang.brigadier.arguments.IntegerArgumentType.*;

public class FullBrightCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext ignoredCommandRegistryAccess) {
        dispatcher.register(ClientCommands.literal("fullbright")
                .executes(context -> FullBright.toggleGamma())
                .then(ClientCommands.argument("value", integer())
                        .executes(context -> FullBright.setValue((double) getInteger(context, "value") / 100))
                )
        );
    }
}
