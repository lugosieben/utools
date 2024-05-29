package net.lugo.utools.config;

import me.shedaniel.autoconfig.ConfigData;
import net.lugo.utools.UTools;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = UTools.MOD_ID)
public class ModConfig  implements ConfigData {
    boolean test1 = true;
    int MyNumber = 3;

    @ConfigEntry.Gui.CollapsibleObject
    CollapsebleConfigs CollapsebleConfigsObject = new CollapsebleConfigs();

    static class CollapsebleConfigs {
        boolean test2 = false;
        double valueOfSomething = 0;
    }
}
