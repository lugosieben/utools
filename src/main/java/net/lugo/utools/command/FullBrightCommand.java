package net.lugo.utools.command;

import net.lugo.utools.features.FullBright;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import static com.mojang.brigadier.arguments.IntegerArgumentType.*;

public class FullBrightCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess ignoredCommandRegistryAccess, CommandManager.RegistrationEnvironment ignoredRegistrationEnvironment) {
        dispatcher.register(CommandManager.literal("fullbright")
                .executes(context -> FullBright.toggleGamma())
                .then(CommandManager.argument("value", integer())
                        .executes(context -> FullBright.setValue((double) getInteger(context, "value") / 100))
                )
        );
    }
}
