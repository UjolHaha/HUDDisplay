package dev.ujol.hud.renderer;

import dev.ujol.config.HUDConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

public class TimeRenderer extends Renderer {
    public TimeRenderer(ClientPlayerEntity player, DrawContext drawContext) {
        HUDConfig config = HUDConfig.instance();
        if (config.timeEnabled) {
            long daytime = player.clientWorld.getTimeOfDay() + 6000;
            int hours = (int) (daytime / 1000) % 24;
            int minutes = (int) ((daytime % 1000) * 60 / 1000);
            String formattedTime = String.format("%02d:%02d", hours, minutes);
            int formattedTimeWidth = calculateBackgroundWidth(formattedTime);


            int x = (int) ((client.getWindow().getScaledWidth()) * config.timeXPercentage / 100.0) - 3;
            int y = (int) (client.getWindow().getScaledHeight() * config.timeYPercentage / 100.0) - 3;

            renderBackground(drawContext, x, y, formattedTimeWidth + 10, 17, config.timeBgColor);
            renderText(drawContext, Text.of(formattedTime), x + 5, y + 5, config.timeTextColor);
        }
    }
}
