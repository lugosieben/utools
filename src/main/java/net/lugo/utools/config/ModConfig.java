package net.lugo.utools.config;

import me.shedaniel.autoconfig.ConfigData;
import net.lugo.utools.UTools;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.lugo.utools.features.ClientTime.ClientTimeType;

@Config(name = UTools.MOD_ID)
public class ModConfig  implements ConfigData {

    @ConfigEntry.BoundedDiscrete(min = 0,max = 1500)
    public int normalGammaPercentage = 100;

    @ConfigEntry.BoundedDiscrete(min = 0,max = 1500)
    public int fullGammaPercentage = 1500;

    public int zoomMultiplier = 3;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 1)
    @ConfigEntry.Gui.Tooltip(count = 3)
    public double zoomSpeed = 0.1;

    @ConfigEntry.BoundedDiscrete(min=1,max = 15)
    public int lightOverlayThreshold = 1;

    public boolean clientWeatherClear = false;
    public ClientTimeType clientTimeType = ClientTimeType.Disabled;
}
