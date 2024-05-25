package net.lugo.utools.features;

import com.mojang.brigadier.context.CommandContext;
import net.lugo.utools.util.HudMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;

public class FullBright {
    private static final SimpleOption<Double>  gamma = MinecraftClient.getInstance().options.getGamma();

    public static int setValue(CommandContext<ServerCommandSource> serverCommandSourceCommandContext) {
        double value = (double) getInteger(serverCommandSourceCommandContext, "value") / 100;
        gamma.setValue(value);
        return 1;
    }

    public static int toggle() {
        double toPut = gamma.getValue() == 15.0 ? 1.0 : 15.0;
        int percentage = (int)toPut * 100;
        MutableText message = Text.translatable("text.utools.message.gammaPercentage", String.valueOf(percentage));
        gamma.setValue(toPut);
        HudMessage.show(message, Formatting.DARK_AQUA);
        return 1;
    }
}
