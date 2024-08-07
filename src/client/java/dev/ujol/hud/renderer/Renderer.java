package dev.ujol.hud.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.text.Text;

import java.awt.*;

public class Renderer {
    protected static final MinecraftClient client = MinecraftClient.getInstance();

    protected void renderText(DrawContext drawContext, Text text, int x, int y, Color color) {
        TextRenderer textRenderer = client.textRenderer;
        drawContext.drawText(textRenderer, text, x, y, color.getRGB(), false);
    }

    protected void renderMultiLineText(DrawContext drawContext, Text[] texts, int x, int y, Color color) {
        TextRenderer textRenderer = client.textRenderer;
        for (int i = 0; i < texts.length; i++) {
            Text text = texts[i];
            drawContext.drawText(textRenderer, text, x, y + (i * 13), color.getRGB(), false);
        }
    }

    protected void renderBackground(DrawContext drawContext, int x, int y, int width, int height, Color color) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        drawContext.fill(x, y, x + width, y + height, color.getRGB());
    }

    protected int calculateBackgroundWidth(String... texts) {
        int totalWidth = 0;
        TextRenderer textRenderer = client.textRenderer;
        for (String text : texts) {
            totalWidth = Math.max(totalWidth, textRenderer.getWidth(text));
        }
        return totalWidth;
    }
}
