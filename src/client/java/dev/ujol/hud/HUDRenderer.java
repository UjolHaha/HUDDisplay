package dev.ujol.hud;

import dev.ujol.config.HUDConfig;
import dev.ujol.hud.renderer.CoordinatesRenderer;
import dev.ujol.hud.renderer.EquipmentsRenderer;
import dev.ujol.hud.renderer.FPSRenderer;
import dev.ujol.hud.renderer.TimeRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class HUDRenderer implements HudRenderCallback {

    private static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        if (client != null) {
            assert client.player != null;
            HUDConfig config = HUDConfig.instance();
            if (!client.isPaused() && !client.options.hudHidden && config.enabled) {
                new FPSRenderer(drawContext);
                new CoordinatesRenderer(client.player, drawContext);
                new TimeRenderer(client.player, drawContext);
                new EquipmentsRenderer(client.player, drawContext);
            }
        }
    }
}
