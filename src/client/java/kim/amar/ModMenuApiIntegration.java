package kim.amar;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import kim.amar.config.HUDConfig;

public class ModMenuApiIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return HUDConfig::getConfigScreen;
    }
}
