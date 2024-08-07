package dev.ujol.hud.renderer;

import dev.ujol.config.HUDConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class EquipmentsRenderer extends Renderer {
    public EquipmentsRenderer(ClientPlayerEntity player, DrawContext drawContext) {
        HUDConfig config = HUDConfig.instance();
        if (config.equipmentsEnabled) {
            renderArmorAndHands(player, drawContext, config);
        }
    }

    private void renderArmorAndHands(ClientPlayerEntity player, DrawContext drawContext, HUDConfig config) {
        ItemStack[] equipments = {
                player.getEquippedStack(EquipmentSlot.MAINHAND),
                player.getEquippedStack(EquipmentSlot.HEAD),
                player.getEquippedStack(EquipmentSlot.CHEST),
                player.getEquippedStack(EquipmentSlot.LEGS),
                player.getEquippedStack(EquipmentSlot.FEET),
                player.getEquippedStack(EquipmentSlot.OFFHAND)
        };

        ArrayList<ItemStack> equippedItems = new ArrayList<>();
        for (ItemStack item : equipments) {
            if (!item.isEmpty()) {
                equippedItems.add(item);
            }
        }

        String widestText = getWidestItemText(equipments);
        int widthBackground = calculateBackgroundWidth(widestText);

        int x = config.equipmentsXOffset;
        int y = config.equipmentsYOffset;

        double guiScale = client.getWindow().getScaleFactor();
        if (guiScale > 0) {
            x /= (int) guiScale;
            y /= (int) guiScale;
        }

        int maxX = client.getWindow().getScaledWidth() - (widthBackground + 30);
        int maxY = client.getWindow().getScaledHeight() - (16 * equippedItems.size() + 10);
        x = Math.min(x, maxX);
        y = Math.min(y, maxY);

        if (!equippedItems.isEmpty()) {
            renderBackground(drawContext, x, y, widthBackground + 30, 16 * equippedItems.size() + 10, config.equipmentsBgColor);
        }

        int iconSize = 16;
        int numberOfItems = 0;
        for (ItemStack equipment : equipments) {
            if (!equipment.isEmpty()) {
                renderItemWithDurability(drawContext, x + 5, y + 5 + numberOfItems * iconSize, equipment, iconSize, config.equipmentsTextColor);
                numberOfItems++;
            }
        }
    }

    private String getWidestItemText(ItemStack[] items) {
        String widestText = "";
        for (ItemStack item : items) {
            String text = item.isDamageable() ? (item.getMaxDamage() - item.getDamage()) + "/" + item.getMaxDamage() :
                    item.getCount() > 0 ? "x" + item.getCount() : "";
            if (text.length() > widestText.length()) {
                widestText = text;
            }
        }
        return widestText;
    }

    private void renderItemWithDurability(DrawContext drawContext, int x, int y, ItemStack itemStack, int iconSize, Color color) {
        if (!itemStack.isEmpty()) {
            drawContext.drawItem(itemStack, x, y);
            if (itemStack.isDamageable()) {
                int durability = itemStack.getMaxDamage() - itemStack.getDamage();
                int maxDurability = itemStack.getMaxDamage();
                renderText(drawContext, Text.of(String.format("%d/%d", durability, maxDurability)), x + iconSize + 2, y + 4, color);
            } else {
                renderText(drawContext, Text.of("x" + itemStack.getCount()), x + iconSize + 2, y + 4, color);
            }
        }
    }
}

