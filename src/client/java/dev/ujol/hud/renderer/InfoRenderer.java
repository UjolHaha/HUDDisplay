package dev.ujol.hud.renderer;

import dev.ujol.config.HUDConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class InfoRenderer extends Renderer {
    public InfoRenderer(ClientPlayerEntity player, DrawContext drawContext) {
        HUDConfig config = HUDConfig.instance();
        if (config.infoEnabled) {
            long daytime = player.clientWorld.getTimeOfDay() + 6000;
            int hours = (int) (daytime / 1000) % 24;
            int minutes = (int) ((daytime % 1000) * 60 / 1000);

            BlockPos playerPos = player.getBlockPos();
            String coordinate = String.format("%d, %d, %d", playerPos.getX(), playerPos.getY(), playerPos.getZ());
            String formattedTime = String.format("%02d:%02d", hours, minutes);
            int fps = client.getCurrentFps();

            int x = config.infoXOffset;
            int y = config.infoYOffset;

            double guiScale = client.getWindow().getScaleFactor();
            if (guiScale > 0) {
                x /= (int) guiScale;
                y /= (int) guiScale;
            }

            String timeAndFps = String.format("%s (%d FPS)", formattedTime, fps);
            int textWidth = calculateBackgroundWidth(timeAndFps, coordinate);

            int maxX = client.getWindow().getScaledWidth() - (textWidth + 10);
            int maxY = client.getWindow().getScaledHeight() - 30;

            x = Math.min(x, maxX);
            y = Math.min(y, maxY);

            Text[] texts = {
                    Text.of(timeAndFps),
                    Text.of(coordinate)
            };

            renderBackground(drawContext, x, y, textWidth + 10, 30, config.infoBgColor);
            renderMultiLineText(drawContext, texts, x + 5, y + 5, config.infoTextColor);
        }
    }
}
