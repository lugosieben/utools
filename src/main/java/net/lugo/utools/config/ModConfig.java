package net.lugo.utools.config;

import me.shedaniel.autoconfig.ConfigData;
import net.lugo.utools.UTools;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.lugo.utools.features.ClientTime.ClientTimeType;

@Config(name = UTools.MOD_ID)
public class ModConfig  implements ConfigData {

    @ConfigEntry.Category("Fullbright")
    @ConfigEntry.BoundedDiscrete(min = 0,max = 1500)
    public int normalGammaPercentage = 100;

    @ConfigEntry.Category("Fullbright")
    @ConfigEntry.BoundedDiscrete(min = 0,max = 1500)
    public int fullGammaPercentage = 1500;

    @ConfigEntry.Category("Fullbright")
    @ConfigEntry.Gui.Tooltip
    public boolean useNightVisionInstead = false;

    @ConfigEntry.Category("Zoom")
    public int zoomMultiplier = 3;

    @ConfigEntry.Category("Zoom")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 1)
    @ConfigEntry.Gui.Tooltip(count = 3)
    public double zoomSpeed = 0.1;

    @ConfigEntry.Category("ClientTimeWeather")
    public boolean clientWeatherClear = false;
    @ConfigEntry.Category("ClientTimeWeather")
    public ClientTimeType clientTimeType = ClientTimeType.Disabled;

    @ConfigEntry.BoundedDiscrete(min=1,max = 15)
    public int lightOverlayThreshold = 1;
    
    @ConfigEntry.Gui.Tooltip()
    public boolean unpinAllTexturePacks = false;
  
    public boolean copyScreenshots = false;
}
