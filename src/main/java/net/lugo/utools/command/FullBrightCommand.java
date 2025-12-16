package net.lugo.utools.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.lugo.utools.features.FullBright;
import net.minecraft.commands.CommandBuildContext;
import com.mojang.brigadier.CommandDispatcher;

import static com.mojang.brigadier.arguments.IntegerArgumentType.*;

public class FullBrightCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext ignoredCommandRegistryAccess) {
        dispatcher.register(ClientCommandManager.literal("fullbright")
                .executes(context -> FullBright.toggleGamma())
                .then(ClientCommandManager.argument("value", integer())
                        .executes(context -> FullBright.setValue((double) getInteger(context, "value") / 100))
                )
        );
    }
}
