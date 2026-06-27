package com.windcharge;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindChargeAutoTrigger implements ModInitializer {
    public static final String MOD_ID = "windcharge-autotrigger";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[Wind Charge Auto Trigger] Initializing mod");
        WindChargeConfig.loadConfig();
    }
}