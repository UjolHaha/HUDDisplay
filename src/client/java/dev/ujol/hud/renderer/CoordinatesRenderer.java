package dev.ujol.hud.renderer;

import dev.ujol.config.HUDConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class CoordinatesRenderer extends Renderer {
    public CoordinatesRenderer(ClientPlayerEntity player, DrawContext drawContext) {
        HUDConfig config = HUDConfig.instance();
        if (config.coordinateEnabled) {
            BlockPos playerPos = player.getBlockPos();
            String coordinate = String.format("%d, %d, %d", playerPos.getX(), playerPos.getY(), playerPos.getZ());
            int coordinateWidth = calculateBackgroundWidth(coordinate);

            int x = (int) (client.getWindow().getScaledWidth() * config.coordinateXPercentage / 100.0) - 3;
            int y = (int) (client.getWindow().getScaledHeight() * config.coordinateYPercentage / 100.0) - 3;

            renderBackground(drawContext, x, y, coordinateWidth + 10, 17, config.coordinateBgColor);
            renderText(drawContext, Text.of(coordinate), x + 5, y + 5, config.coordinateTextColor);
        }
    }
}
