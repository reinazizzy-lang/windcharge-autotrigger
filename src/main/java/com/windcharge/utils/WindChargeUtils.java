package com.windcharge.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Items;

public class WindChargeUtils {
    /**
     * Finds Wind Charge in player's hotbar (slots 0-8)
     * @param player The player to check
     * @return Slot index if found, -1 otherwise
     */
    public static int findWindChargeInHotbar(Player player) {
        InventoryMenu inventory = player.containerMenu instanceof InventoryMenu ? (InventoryMenu) player.containerMenu : null;
        
        if (inventory == null) {
            return -1;
        }

        // Check hotbar slots (0-8)
        for (int i = 0; i < 9; i++) {
            if (inventory.getSlot(i).getItem().is(Items.WIND_CHARGE)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Checks if wind charge can deal enough damage to kill the target
     * @param targetHealth Current health of target
     * @param windChargeDamage Damage wind charge deals
     * @return true if wind charge can kill
     */
    public static boolean canKillWithWindCharge(double targetHealth, double windChargeDamage) {
        return targetHealth <= windChargeDamage;
    }

    /**
     * Gets wind charge damage (typically 2.5 to 5 hearts depending on distance)
     * @return Estimated wind charge damage
     */
    public static double getWindChargeDamage() {
        // Wind charge deals approximately 2-5 damage depending on distance and direction
        // Conservative estimate for close range
        return 2.5;
    }
}