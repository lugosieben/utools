package net.lugo.utools.features;

import net.lugo.utools.config.ModConfig;

public class ClientTime {
    public static void set(ClientTime.ClientTimeType timeType) {
        ModConfig.clientTimeType = timeType;
    }

    public static Long forcedGameTime() {
        return switch (ModConfig.clientTimeType) {
            case DISABLED -> null;
            case DAY -> 1000L;
            case NOON -> 6000L;
            case NIGHT -> 13000L;
            case MIDNIGHT -> 18000L;
            case CUSTOM -> (long) ModConfig.customClientTime;
        };
    }

    public enum ClientTimeType {
        DISABLED,
        DAY,
        MIDNIGHT,
        NIGHT,
        NOON,
        CUSTOM
    }
}
