package kim.amar.hud.renderer;

import kim.amar.config.HUDConfig;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class EquipmentsRenderer extends Renderer {
    public EquipmentsRenderer(ClientPlayerEntity player, DrawContext drawContext) {
        HUDConfig config = HUDConfig.instance();
        if (config.armorAndItemEnabled) {
            renderArmorAndHands(player, drawContext, config);
        }
        if (config.elytraTimeEnabled) {
            renderElytra(player, drawContext, config);
        }
    }

    private void renderArmorAndHands(ClientPlayerEntity player, DrawContext drawContext, HUDConfig config) {
        TextRenderer textRenderer = client.textRenderer;
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

        int x = (int) (client.getWindow().getScaledWidth() * config.armorAndItemXPercentage / 100.0);
        int y = (int) (client.getWindow().getScaledHeight() * config.armorAndItemYPercentage / 100.0);
        String widestText = getWidestItemText(equipments);
        int widthBackground = calculateBackgroundWidth(widestText);

        if (!equippedItems.isEmpty()) {
            renderBackground(drawContext, x - 3, y - 3, 25 + widthBackground, 16 * equippedItems.size() + 6, config.armorAndItemBgColor);
        }

        int iconSize = 16;
        int numberOfItems = 0;
        for (ItemStack equipment : equipments) {
            if (!equipment.isEmpty()) {
                renderItemWithDurability(drawContext, x, y + numberOfItems * iconSize, equipment, iconSize, config.armorAndItemTextColor);
                numberOfItems++;
            }
        }
    }

    private void renderElytra(ClientPlayerEntity player, DrawContext drawContext, HUDConfig config) {
        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);
        if (chestplate.getItem() == Items.ELYTRA) {
            assert client.world != null;
            RegistryEntry<Enchantment> entry = client.world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.UNBREAKING).get();
            int unbreakingLevel = EnchantmentHelper.getLevel(entry, chestplate);
            int timeRemaining = determineTimeLeft(chestplate) - unbreakingLevel;
            String elytraTimeLeftString = formatTime(timeRemaining);
            int elytraTimeLeftWidth = calculateBackgroundWidth(elytraTimeLeftString);

            int x = (int) ((client.getWindow().getScaledWidth()) * config.elytraTimeXPercentage / 100.0) - 3;
            int y = (int) (client.getWindow().getScaledHeight() * config.elytraTimeYPercentage / 100.0) - 3;

            renderBackground(drawContext, x, y, elytraTimeLeftWidth + 10, 17, config.elytraTimeBgColor);
            renderText(drawContext, Text.of(elytraTimeLeftString), x + 5, y + 5, config.elytraTimeTextColor);
        }
    }

    private int determineTimeLeft(ItemStack item) {
        assert client.world != null;
        RegistryEntry<Enchantment> entry = client.world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.UNBREAKING).get();
        int unbreakingLevel = EnchantmentHelper.getLevel(entry, item);
        return ((item.getMaxDamage() - item.getDamage()) * (unbreakingLevel + 1)) - 1;
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
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

