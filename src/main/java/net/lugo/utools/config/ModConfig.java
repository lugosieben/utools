package net.lugo.utools.config;

import me.shedaniel.autoconfig.ConfigData;
import net.lugo.utools.UTools;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.lugo.utools.features.ClientTime.ClientTimeType;
import net.lugo.utools.util.Easing;

@SuppressWarnings("CanBeFinal")
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
    @ConfigEntry.Gui.Tooltip
    public float zoomMultiplier = 3f;
    @ConfigEntry.Category("Zoom")
    @ConfigEntry.Gui.Tooltip
    public float scrollIncrement = 1f;
    @ConfigEntry.Category("Zoom")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 10)
    @ConfigEntry.Gui.Tooltip()
    public int zoomDuration = 2;
    @ConfigEntry.Category("Zoom")
    public Easing zoomInEasing = Easing.EASE_OUT_EXPONENTIAL;
    @ConfigEntry.Category("Zoom")
    public Easing zoomOutEasing = Easing.EASE_OUT_EXPONENTIAL;
    @ConfigEntry.Category("Zoom")
    public boolean zoomRelativeSensitivity = true;
    @ConfigEntry.Category("Zoom")
    public boolean zoomSmoothCam = false;
    @ConfigEntry.Category("Zoom")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean turnOffZoom = false;

    @ConfigEntry.Category("ClientTimeWeather")
    public boolean clientWeatherClear = false;
    @ConfigEntry.Category("ClientTimeWeather")
    public ClientTimeType clientTimeType = ClientTimeType.DISABLED;
    @ConfigEntry.Category("ClientTimeWeather")
    @ConfigEntry.Gui.Tooltip()
    public int customClientTime = 0;
    
    @ConfigEntry.Category("VisualModifications")
    public boolean hidePumpkinOverlay = false;
    @ConfigEntry.Category("VisualModifications")
    @ConfigEntry.BoundedDiscrete(min=-100, max = 0)
    public int lowFireModifier = 0;
    @ConfigEntry.Category("VisualModifications")
    public boolean hideFireWhenResistant = false;
    @ConfigEntry.Category("VisualModifications")
    @ConfigEntry.BoundedDiscrete(min=-100, max = 0)
    public int lowShieldModifier = 0;
    @ConfigEntry.Category("VisualModifications")
    public boolean mapInBoat = false;
    @ConfigEntry.Category("VisualModifications")
    @ConfigEntry.BoundedDiscrete(min=0, max = 100)
    public float totemSize = 100;
    @ConfigEntry.Category("VisualModifications")
    @ConfigEntry.BoundedDiscrete(min=0, max = 100)
    public float totemPopSize = 100;
    @ConfigEntry.Category("VisualModifications")
    public boolean showBarriers = false;
  
    @ConfigEntry.Category("Fog")
    public boolean turnOffAllFogs = false;
    @ConfigEntry.Category("Fog")
    public boolean terrainFog = true;
    @ConfigEntry.Category("Fog")
    public boolean lavaFog = true;
    @ConfigEntry.Category("Fog")
    public boolean powderSnowFog = true;
    @ConfigEntry.Category("Fog")
    public boolean blindnessFog = true;
    @ConfigEntry.Category("Fog")
    public boolean darknessFog = true;
    @ConfigEntry.Category("Fog")
    public boolean waterFog = true;
    @ConfigEntry.Category("Fog")
    public boolean renderDistanceFog = true;

    @ConfigEntry.Category("Gameplay")
    public boolean keepMining = false;

    @ConfigEntry.BoundedDiscrete(min=1,max = 15)
    public int lightOverlayThreshold = 1;
    
    @ConfigEntry.Gui.Tooltip()
    public boolean unpinAllTexturePacks = false;
  
    public boolean copyScreenshots = false;
}
