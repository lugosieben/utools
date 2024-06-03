package net.lugo.utools.features;

import net.lugo.utools.UTools;

public class ClientTime {
    public static void set(ClientTime.ClientTimeType timeType) {
        UTools.CONFIG.clientTimeType = timeType;
    }

    public enum ClientTimeType {
        Disabled,
        Day,
        Midnight,
        Night,
        Noon
    }
}
