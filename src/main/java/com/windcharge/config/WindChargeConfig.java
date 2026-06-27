package com.windcharge.config;

import com.windcharge.WindChargeAutoTrigger;

public class WindChargeConfig {
    public static boolean enabled = false;
    public static int triggerDelay = 100; // milliseconds
    public static int toggleKey = 72; // H key
    public static double healthThreshold = 2.0; // damage wind charge can deal
    public static boolean highlightEnemy = true;
    public static double attackRange = 16.0; // blocks

    public static void loadConfig() {
        WindChargeAutoTrigger.LOGGER.info("[Wind Charge] Config loaded");
        WindChargeAutoTrigger.LOGGER.info("Enabled: " + enabled);
        WindChargeAutoTrigger.LOGGER.info("Trigger Delay: " + triggerDelay + "ms");
        WindChargeAutoTrigger.LOGGER.info("Toggle Key: " + toggleKey);
        WindChargeAutoTrigger.LOGGER.info("Health Threshold: " + healthThreshold);
        WindChargeAutoTrigger.LOGGER.info("Attack Range: " + attackRange + " blocks");
    }

    public static void setEnabled(boolean state) {
        enabled = state;
        WindChargeAutoTrigger.LOGGER.info("[Wind Charge] Trigger " + (enabled ? "ENABLED" : "DISABLED"));
    }

    public static void setTriggerDelay(int delay) {
        triggerDelay = Math.max(50, Math.min(1000, delay));
        WindChargeAutoTrigger.LOGGER.info("[Wind Charge] Trigger delay set to: " + triggerDelay + "ms");
    }

    public static void setHealthThreshold(double threshold) {
        healthThreshold = Math.max(0.5, Math.min(20.0, threshold));
        WindChargeAutoTrigger.LOGGER.info("[Wind Charge] Health threshold set to: " + healthThreshold);
    }
}