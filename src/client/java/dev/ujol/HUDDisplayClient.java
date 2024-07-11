package dev.ujol;


import dev.ujol.config.HUDConfig;
import dev.ujol.hud.HUDRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HUDDisplayClient implements ClientModInitializer {
    public static final String MOD_ID = "huddisplay";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing HUDDisplay");

        HUDConfig.load();
        HudRenderCallback.EVENT.register(new HUDRenderer());
        HUDDisplayKeyBinding.register();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (HUDDisplayKeyBinding.getKeyBinding().isPressed()) {
                client.setScreen(HUDConfig.getConfigScreen(client.currentScreen));
            }
        });
    }
}