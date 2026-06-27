package com.windcharge.client;

import com.windcharge.events.ClientTickEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class WindChargeClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvent.registerTickEvent();
    }
}