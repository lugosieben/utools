<p align="center">
<img height="200" src="src/main/resources/assets/utools/icon.png" alt="Logo of UTools">
</p>

[![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/utools)
[![Requires Fabric API](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/supported/fabric_vector.svg)](https://fabricmc.net/)

[![License Badge](https://img.shields.io/badge/License-CC--BY--NC--4.0-blue)](https://creativecommons.org/licenses/by-nc/4.0/)
[![GitHub Release](https://img.shields.io/github/v/release/lugosieben/utools)](https://github.com/lugosieben/utools/releases/latest)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/t/lugosieben/utools)](https://github.com/lugosieben/utools/commits/)

_Simple tools for the survival experience. This should act as a replacement to having tons of mods for things like Fullbright, Zoom etc._

# Features

## Short Overview
- Fullbright / Custom Gamma / Night Vision
- Zoom
- Light Overlay
- Client Side Time & Weather
- Unpin all texture packs (Forced Server Resource Pack Bypass)
- Auto-Copy Screenshots
- Hide Pumpkin Overlay
- Lower Fire & Shield (Customizable)
- Turn off different types of Fog individually or all together

## Fullbright / Gamma / Night Vision

![Gamma Comparison](assets/gammacomparism.png)

Change your Gamma above the normal limits. The intended usage is to get Fullbright, but the Gamma can also be changed to a low value.
If you're using shaders, you should use Night Vision instead of custom gamma.

#### Usage

Use `/fullbright` or the `Toggle Fullbright` keybinding to switch between 1500% (Max) or 100% (Default) gamma.

Use `/fullbright [value]` to change your gamma to any positive value.!

Use the `Toggle Nightvision` keybinding to toggle the clientside night vision effect.

You can toggle the normal / fullbright Gamma values in the settings (Accessible via [ModMenu](https://modrinth.com/mod/modmenu))

## Zoom

Zoom in (or out) with a simple hotkey. You can use a smooth zoom or an instant zoom.

#### Usage

Hold the `Zoom` keybinding to switch into zoom mode.

Change the zoom multiplier (how far it zooms in) in the settings.
To modify the zoom animation, change the `Zoom Speed` (Number from 0 to 1, 1 = Instant)

## Light Overlay

![Light Overlay Example](assets/lightoverlayexample.png)

Display the light levels of nearby blocks in real-time. Useful to spawn-proof with torches.

#### Usage

Use the `Toggle Light Overlay` keybinding to toggle on or off. 

You can set the threshold for a green texture in the settings under `Light Overlay Threshold`.

## Client Time & Client Weather

Change your local time to `DAY,NOON,NIGHT,MIDNIGHT` and disable rain. This also works on servers since it is only client side.

#### Usage

You can change the time and weather in the settings (`/utools config` or in ModMenu).
Alternatively you can change the time with `/clienttime set day|noon|night|midnight` and reset with `/clienttime reset`.

## Unpin Texture Packs

This allows you to unpin all texture packs. Doing so, you can move all texture packs independent of their intended order. This is especially useful when a server forces a resource pack which you don't want. To bypass, you can just move the server pack to the bottom, below the default textures since it is no longer pinned.

#### Usage

The setting to unpin all packs is located in the settings (`/utools config` or via ModMenu)

## Copy Screenshots

When taking screenshots, the images will be automatically copied to the clipboard.

#### Usage

Toggleable in the settings

## Several Visual Modifications

- Hide the pumpkin on head overlay
- Lower fire overlay (customizable)
- Lower shield (customizable)

#### Usage

Toggle / Customize in the config under the category `Visual Modifications`

## Custom Fog

Allows you to enable / disable ALL types of fogs. See comparison in the nether for Terrain Fog below

![Image](https://github.com/lugosieben/utools/blob/latest/assets/fogcomparison.png?raw=true)

#### Usage

See all fog types in config under `Fog` category. Turn them off to your likings.

**Caution**: Disabling fogs, especially darkness, blindness, lava and water fogs could be considered cheating or an unfair advantage by some players or server administrators.
 
# Translations

`en_en` English: [@lugosieben](https://github.com/lugosieben) (Must be updated with any new feature!)

`de_de` German: [@Kolpixx](https://github.com/Kolpixx)

`es_es` Spanish: [@lugosieben](https://github.com/lugosieben)

# License

This project is licensed under [Creative Commons Attribution-NonCommercial 4.0 International](https://creativecommons.org/licenses/by-nc/4.0/deed.en).
