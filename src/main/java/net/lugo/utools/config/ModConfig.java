package net.lugo.utools.config;

import me.shedaniel.autoconfig.ConfigData;
import net.lugo.utools.UTools;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = UTools.MOD_ID)
public class ModConfig  implements ConfigData {

    @ConfigEntry.BoundedDiscrete(min = 0,max = 1500)
    public int normalGammaPercentage = 100;

    @ConfigEntry.BoundedDiscrete(min = 0,max = 1500)
    public int fullGammaPercentage = 1500;

    public int zoomMultiplier = 3;
}
