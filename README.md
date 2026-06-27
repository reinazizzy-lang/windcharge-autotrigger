# Wind Charge Auto Trigger

A Fabric mod for Minecraft 1.21.1 that automatically throws Wind Charges at low-health enemies when your crosshair is over them. Works like a trigger-bot with customizable delay and hotkey.

## Features

✅ **Auto Wind Charge Throw** - Automatically uses Wind Charge when:
- Crosshair is targeting an enemy
- Enemy health is low enough to be killed by Wind Charge
- Wind Charge is in your hotbar

✅ **Customizable** - Configure:
- Trigger delay (50-1000ms)
- Health threshold
- Attack range (default 16 blocks)
- Toggle hotkey (default H)

✅ **Toggle On/Off** - Press configurable hotkey to enable/disable

✅ **Smart Detection** - Only triggers on valid targets

## Installation

1. Download the latest build from [Releases](https://github.com/reinazizzy-lang/windcharge-autotrigger/releases)
2. Place the `.jar` file in your `mods` folder
3. Make sure you have [Fabric Loader](https://fabricmc.net/) installed
4. Launch Minecraft with the Fabric profile

## Configuration

Edit settings in `WindChargeConfig.java`:

```java
enabled = false;              // Toggle enabled state
triggerDelay = 100;           // Delay between triggers (ms)
toggleKey = 72;               // H key (GLFW key code)
healthThreshold = 2.0;        // Enemy health to trigger
attackRange = 16.0;           // Max distance to target (blocks)
```

## Usage

1. Place Wind Charges in your hotbar
2. Press `H` (default) to toggle the mod on/off
3. Look at an enemy with low health
4. When crosshair is on them and health is low, Wind Charge automatically throws!

## Default Keybinds

| Key | Action |
|-----|--------|
| **H** | Toggle Auto Trigger On/Off |

## How It Works

1. Continuously monitors the entity under your crosshair
2. Checks if the entity is a valid target (living, not invisible)
3. Verifies health is low enough to be killed by Wind Charge
4. Automatically switches to Wind Charge in hotbar
5. Throws the Wind Charge
6. Respects configurable delay between triggers

## Requirements

- Minecraft 1.21.1
- Fabric Loader 0.15.11+
- Java 21+

## Building

```bash
./gradlew build
```

The compiled mod will be in `build/libs/`

## License

MIT License - Feel free to use, modify, and distribute!

## Support

For issues or feature requests, please open an [Issue](https://github.com/reinazizzy-lang/windcharge-autotrigger/issues) on GitHub.

---

**Note**: This mod is for PvP gameplay. Use responsibly!