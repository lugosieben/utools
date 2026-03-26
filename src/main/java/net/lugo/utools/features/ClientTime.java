package net.lugo.utools.features;

import net.lugo.utools.config.ModConfig;

public class ClientTime {
    public static void set(ClientTime.ClientTimeType timeType) {
        ModConfig.clientTimeType = timeType;
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
