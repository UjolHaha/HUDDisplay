package dev.ujol;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.ujol.config.HUDConfig;

public class ModMenuApiIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return HUDConfig::getConfigScreen;
    }
}
