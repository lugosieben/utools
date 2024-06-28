package net.lugo.utools.features;

import net.lugo.utools.UTools;

public class ClientTime {
    public static void set(ClientTime.ClientTimeType timeType) {
        UTools.getConfig().clientTimeType = timeType;
    }

    public enum ClientTimeType {
        DISABLED,
        DAY,
        MIDNIGHT,
        NIGHT,
        NOON
    }
}
