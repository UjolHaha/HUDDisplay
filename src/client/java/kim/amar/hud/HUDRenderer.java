package kim.amar.hud;

import kim.amar.config.HUDConfig;
import kim.amar.hud.renderer.CoordinatesRenderer;
import kim.amar.hud.renderer.EquipmentsRenderer;
import kim.amar.hud.renderer.FPSRenderer;
import kim.amar.hud.renderer.TimeRenderer;
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
            if (config.enabled) {
                new FPSRenderer(drawContext);
                new CoordinatesRenderer(client.player, drawContext);
                new TimeRenderer(client.player, drawContext);
                new EquipmentsRenderer(client.player, drawContext);
            }
        }
    }
}
