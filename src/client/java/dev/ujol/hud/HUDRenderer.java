package dev.ujol.hud;

import dev.ujol.config.HUDConfig;
import dev.ujol.hud.renderer.EquipmentsRenderer;
import dev.ujol.hud.renderer.InfoRenderer;
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

            if (!client.options.hudHidden && !client.getDebugHud().shouldShowDebugHud() && config.enabled) {
                new InfoRenderer(client.player, drawContext);
                new EquipmentsRenderer(client.player, drawContext);
            }
        }
    }
}
