package net.lugo.utools.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.lugo.utools.UTools;
import net.lugo.utools.features.ClientTime.ClientTimeType;
import net.lugo.utools.util.Easing;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ModConfig {

	// Fullbright
	@SerialEntry
	public static int normalGammaPercentage = 100;

	@SerialEntry
	public static int fullGammaPercentage = 1500;

	@SerialEntry
	public static boolean useNightVisionInstead = false;

	// Zoom
	@SerialEntry
	public static float zoomMultiplier = 3f;

	@SerialEntry
	public static float scrollIncrement = 1f;

	@SerialEntry
	public static int zoomDuration = 2;

	@SerialEntry
	public static Easing zoomInEasing = Easing.EASE_OUT_EXPONENTIAL;

	@SerialEntry
	public static Easing zoomOutEasing = Easing.EASE_OUT_EXPONENTIAL;

	@SerialEntry
	public static boolean zoomRelativeSensitivity = true;

	@SerialEntry
	public static boolean zoomSmoothCam = false;

	@SerialEntry
	public static boolean turnOffZoom = false;

	// Client Time/Weather
	@SerialEntry
	public static boolean clientWeatherClear = false;

	@SerialEntry
	public static ClientTimeType clientTimeType = ClientTimeType.DISABLED;

	@SerialEntry
	public static int customClientTime = 0;

	// Visual Modifications
	@SerialEntry
	public static boolean hidePumpkinOverlay = false;

	@SerialEntry
	public static int lowFireModifier = 0;

	@SerialEntry
	public static boolean hideFireWhenResistant = false;

	@SerialEntry
	public static int lowShieldModifier = 0;

	@SerialEntry
	public static boolean mapInBoat = false;

	@SerialEntry
	public static int totemSize = 100;

	@SerialEntry
	public static int totemPopSize = 100;

	@SerialEntry
	public static boolean showBarriers = false;

	// Fog
	@SerialEntry
	public static boolean turnOffAllFogs = false;

	@SerialEntry
	public static boolean terrainFog = true;

	@SerialEntry
	public static boolean lavaFog = true;

	@SerialEntry
	public static boolean powderSnowFog = true;

	@SerialEntry
	public static boolean blindnessFog = true;

	@SerialEntry
	public static boolean darknessFog = true;

	@SerialEntry
	public static boolean waterFog = true;

	@SerialEntry
	public static boolean renderDistanceFog = true;

	// Gameplay
	@SerialEntry
	public static boolean keepMining = false;

	// Light Overlay
	@SerialEntry
	public static int lightOverlayThreshold = 1;

	// Other
	@SerialEntry
	public static boolean unpinAllTexturePacks = false;

	@SerialEntry
	public static boolean copyScreenshots = false;

	public static Screen makeScreen(Screen parent) {
		return YetAnotherConfigLib.createBuilder()
				.title(Component.translatable("text.utools.config.title"))
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.fullbright"))
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.normal_gamma_percentage.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.normal_gamma_percentage.description")))
								.binding(100, () -> normalGammaPercentage, newVal -> normalGammaPercentage = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 1500).step(1))
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.full_gamma_percentage.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.full_gamma_percentage.description")))
								.binding(1500, () -> fullGammaPercentage, newVal -> fullGammaPercentage = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 1500).step(1))
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.use_night_vision_instead.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.use_night_vision_instead.description")))
								.binding(false, () -> useNightVisionInstead, newVal -> useNightVisionInstead = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.build())
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.zoom"))
						.option(Option.<Float>createBuilder()
								.name(Component.translatable("text.utools.config.option.zoom_multiplier.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.zoom_multiplier.description")))
								.binding(3f, () -> zoomMultiplier, newVal -> zoomMultiplier = newVal)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.option(Option.<Float>createBuilder()
								.name(Component.translatable("text.utools.config.option.scroll_increment.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.scroll_increment.description")))
								.binding(1f, () -> scrollIncrement, newVal -> scrollIncrement = newVal)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.zoom_duration.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.zoom_duration.description")))
								.binding(2, () -> zoomDuration, newVal -> zoomDuration = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 10).step(1))
								.build())
						.option(Option.<Easing>createBuilder()
								.name(Component.translatable("text.utools.config.option.zoom_in_easing.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.zoom_in_easing.description")))
								.binding(Easing.EASE_OUT_EXPONENTIAL, () -> zoomInEasing, newVal -> zoomInEasing = newVal)
								.controller(opt -> EnumControllerBuilder.create(opt)
										.enumClass(Easing.class)
										.formatValue(v -> Component.literal(v.name())))
								.build())
						.option(Option.<Easing>createBuilder()
								.name(Component.translatable("text.utools.config.option.zoom_out_easing.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.zoom_out_easing.description")))
								.binding(Easing.EASE_OUT_EXPONENTIAL, () -> zoomOutEasing, newVal -> zoomOutEasing = newVal)
								.controller(opt -> EnumControllerBuilder.create(opt)
										.enumClass(Easing.class)
										.formatValue(v -> Component.literal(v.name())))
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.zoom_relative_sensitivity.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.zoom_relative_sensitivity.description")))
								.binding(true, () -> zoomRelativeSensitivity, newVal -> zoomRelativeSensitivity = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.zoom_smooth_cam.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.zoom_smooth_cam.description")))
								.binding(false, () -> zoomSmoothCam, newVal -> zoomSmoothCam = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.turn_off_zoom.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.turn_off_zoom.description")))
								.binding(false, () -> turnOffZoom, newVal -> turnOffZoom = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.build())
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.client_time_weather"))
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.client_weather_clear.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.client_weather_clear.description")))
								.binding(false, () -> clientWeatherClear, newVal -> clientWeatherClear = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<ClientTimeType>createBuilder()
								.name(Component.translatable("text.utools.config.option.client_time_type.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.client_time_type.description")))
								.binding(ClientTimeType.DISABLED, () -> clientTimeType, newVal -> clientTimeType = newVal)
								.controller(opt -> EnumControllerBuilder.create(opt)
										.enumClass(ClientTimeType.class)
										.formatValue(v -> Component.literal(v.name())))
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.custom_client_time.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.custom_client_time.description")))
								.binding(0, () -> customClientTime, newVal -> customClientTime = newVal)
								.controller(IntegerFieldControllerBuilder::create)
								.build())
						.build())
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.visual_modifications"))
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.hide_pumpkin_overlay.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.hide_pumpkin_overlay.description")))
								.binding(false, () -> hidePumpkinOverlay, newVal -> hidePumpkinOverlay = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.low_fire_modifier.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.low_fire_modifier.description")))
								.binding(0, () -> lowFireModifier, newVal -> lowFireModifier = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(-100, 0).step(1))
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.hide_fire_when_resistant.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.hide_fire_when_resistant.description")))
								.binding(false, () -> hideFireWhenResistant, newVal -> hideFireWhenResistant = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.low_shield_modifier.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.low_shield_modifier.description")))
								.binding(0, () -> lowShieldModifier, newVal -> lowShieldModifier = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(-100, 0).step(1))
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.map_in_boat.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.map_in_boat.description")))
								.binding(false, () -> mapInBoat, newVal -> mapInBoat = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.totem_size.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.totem_size.description")))
								.binding(100, () -> totemSize, newVal -> totemSize = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 100).step(1))
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.totem_pop_size.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.totem_pop_size.description")))
								.binding(100, () -> totemPopSize, newVal -> totemPopSize = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(0, 100).step(1))
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.show_barriers.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.show_barriers.description")))
								.binding(false, () -> showBarriers, newVal -> showBarriers = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.build())
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.fog"))
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.turn_off_all_fogs.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.turn_off_all_fogs.description")))
								.binding(false, () -> turnOffAllFogs, newVal -> turnOffAllFogs = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.terrain_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.terrain_fog.description")))
								.binding(true, () -> terrainFog, newVal -> terrainFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.lava_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.lava_fog.description")))
								.binding(true, () -> lavaFog, newVal -> lavaFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.powder_snow_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.powder_snow_fog.description")))
								.binding(true, () -> powderSnowFog, newVal -> powderSnowFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.blindness_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.blindness_fog.description")))
								.binding(true, () -> blindnessFog, newVal -> blindnessFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.darkness_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.darkness_fog.description")))
								.binding(true, () -> darknessFog, newVal -> darknessFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.water_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.water_fog.description")))
								.binding(true, () -> waterFog, newVal -> waterFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.render_distance_fog.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.render_distance_fog.description")))
								.binding(true, () -> renderDistanceFog, newVal -> renderDistanceFog = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.build())
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.gameplay"))
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.keep_mining.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.keep_mining.description")))
								.binding(false, () -> keepMining, newVal -> keepMining = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("text.utools.config.option.light_overlay_threshold.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.light_overlay_threshold.description")))
								.binding(1, () -> lightOverlayThreshold, newVal -> lightOverlayThreshold = newVal)
								.controller(opt -> IntegerSliderControllerBuilder.create(opt).range(1, 15).step(1))
								.build())
						.build())
				.category(ConfigCategory.createBuilder()
						.name(Component.translatable("text.utools.config.category.other"))
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.unpin_all_texture_packs.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.unpin_all_texture_packs.description")))
								.binding(false, () -> unpinAllTexturePacks, newVal -> unpinAllTexturePacks = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.option(Option.<Boolean>createBuilder()
								.name(Component.translatable("text.utools.config.option.copy_screenshots.name"))
								.description(OptionDescription.of(Component.translatable("text.utools.config.option.copy_screenshots.description")))
								.binding(false, () -> copyScreenshots, newVal -> copyScreenshots = newVal)
								.controller(TickBoxControllerBuilder::create)
								.build())
						.build())
				.save(HANDLER::save)
				.build()
				.generateScreen(parent);
	}

	public static final ConfigClassHandler<ModConfig> HANDLER = ConfigClassHandler.createBuilder(ModConfig.class)
			.id(Identifier.fromNamespaceAndPath(UTools.MOD_ID, "config"))
			.serializer(config -> GsonConfigSerializerBuilder.create(config)
					.setPath(FabricLoader.getInstance().getConfigDir().resolve("utools.json5"))
					.setJson5(true)
					.build())
			.build();
}
