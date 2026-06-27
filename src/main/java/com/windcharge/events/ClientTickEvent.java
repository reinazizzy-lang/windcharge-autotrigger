package com.windcharge.events;

import com.windcharge.WindChargeAutoTrigger;
import com.windcharge.config.WindChargeConfig;
import com.windcharge.utils.WindChargeUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ClientTickEvent {
    private static long lastTriggerTime = 0;

    public static void registerTickEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvent::onClientTick);
    }

    public static void onClientTick(Minecraft client) {
        if (client.player == null || client.level == null) return;

        LocalPlayer player = client.player;
        
        // Handle toggle key
        if (isKeyPressed(WindChargeConfig.toggleKey)) {
            WindChargeConfig.setEnabled(!WindChargeConfig.enabled);
            try {
                Thread.sleep(200); // Debounce
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (!WindChargeConfig.enabled) return;

        // Check if crosshair is on enemy
        if (client.crosshairPickEntity instanceof LivingEntity target) {
            if (target instanceof Player && !isValidTarget(target)) return;
            if (target.isAlive() && shouldTrigger(player, target)) {
                triggerWindCharge(player, target);
            }
        }
    }

    private static boolean isKeyPressed(int key) {
        long window = Minecraft.getInstance().getWindow().getWindow();
        return GLFW.glfwGetKey(window, key) == GLFW.GLFW_PRESS;
    }

    private static boolean isValidTarget(Entity entity) {
        if (entity instanceof Player target) {
            return !target.isInvisible();
        }
        return true;
    }

    private static boolean shouldTrigger(Player player, LivingEntity target) {
        // Check delay
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTriggerTime < WindChargeConfig.triggerDelay) {
            return false;
        }

        // Check range
        double distance = player.distanceTo(target);
        if (distance > WindChargeConfig.attackRange) {
            return false;
        }

        // Check if enemy is low enough to be killed by wind charge
        double targetHealth = target.getHealth();
        return targetHealth <= WindChargeConfig.healthThreshold;
    }

    private static void triggerWindCharge(LocalPlayer player, LivingEntity target) {
        int windChargeSlot = WindChargeUtils.findWindChargeInHotbar(player);
        
        if (windChargeSlot == -1) {
            WindChargeAutoTrigger.LOGGER.warn("[Wind Charge] Wind Charge not found in hotbar!");
            return;
        }

        // Switch to wind charge
        if (player.getInventory().selected != windChargeSlot) {
            player.getInventory().selected = windChargeSlot;
        }

        // Use wind charge
        Minecraft client = Minecraft.getInstance();
        if (client.gameMode != null) {
            client.gameMode.useItem(player, client.level, player.getUsedItemHand());
        }

        lastTriggerTime = System.currentTimeMillis();
        WindChargeAutoTrigger.LOGGER.info("[Wind Charge] Triggered on " + target.getName().getString());
    }
}