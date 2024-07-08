package kim.amar.hud.renderer;

import kim.amar.config.HUDConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class CoordinatesRenderer extends Renderer {
    public CoordinatesRenderer(ClientPlayerEntity player, DrawContext drawContext) {
        BlockPos playerPos = player.getBlockPos();
        String coordinate = String.format("%d, %d, %d", playerPos.getX(), playerPos.getY(), playerPos.getZ());

        int width = calculateBackgroundWidth(coordinate);
        HUDConfig config = HUDConfig.instance();

        int x = (int) ((client.getWindow().getScaledWidth() - width) * config.coordinateXPercentage / 100.0) - 3;
        int y = (int) (((client.getWindow().getScaledHeight()) * config.coordinateYPercentage) / 100.0) - 3;

        renderBackground(drawContext, x, y, 9 + width, 17, config.coordinateBgColor);
        renderText(drawContext, Text.of(coordinate), x + 5, y + 5, config.coordinateTextColor);
    }
}
