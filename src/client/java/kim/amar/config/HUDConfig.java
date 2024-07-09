package kim.amar.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.ColorField;
import dev.isxander.yacl3.config.v2.api.autogen.IntSlider;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import kim.amar.HUDDisplayClient;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;

import java.awt.*;

public class HUDConfig {
    public static ConfigClassHandler<HUDConfig> HANDLER = ConfigClassHandler.createBuilder(HUDConfig.class)
            .id(YACLPlatform.rl(HUDDisplayClient.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve(HUDDisplayClient.MOD_ID + "/config.json"))
                    .build())
            .build();

    @AutoGen(category = "coordinate")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int coordinateXPercentage = 1;
    @AutoGen(category = "coordinate")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int coordinateYPercentage = 2;
    @AutoGen(category = "coordinate")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color coordinateTextColor = Color.white;
    @AutoGen(category = "coordinate")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color coordinateBgColor = new Color(0x80000000, true);

    @AutoGen(category = "armorAndItem")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int armorAndItemXPercentage = 1;
    @AutoGen(category = "armorAndItem")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int armorAndItemYPercentage = 40;
    @AutoGen(category = "armorAndItem")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color armorAndItemTextColor = Color.white;
    @AutoGen(category = "armorAndItem")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color armorAndItemBgColor = new Color(0x80000000, true);

    @AutoGen(category = "elytra")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int elytraXPercentage = 20;
    @AutoGen(category = "elytra")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int elytraYPercentage = 8;
    @AutoGen(category = "elytra")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color elytraTextColor = Color.white;
    @AutoGen(category = "elytra")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color elytraBgColor = new Color(0x80000000, true);

    @AutoGen(category = "time")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int timeXPercentage = 20;
    @AutoGen(category = "time")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int timeYPercentage = 2;
    @AutoGen(category = "time")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color timeTextColor = Color.white;
    @AutoGen(category = "time")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color timeBgColor = new Color(0x80000000, true);

    @AutoGen(category = "fps")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int fpsXPercentage = 98;
    @AutoGen(category = "fps")
    @IntSlider(min = 1, max = 100, step = 1)
    @SerialEntry
    public int fpsYPercentage = 2;
    @AutoGen(category = "fps")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color fpsTextColor = Color.white;
    @AutoGen(category = "fps")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color fpsBgColor = new Color(0x80000000, true);

    public static void load() {
        HANDLER.load();
    }

    public static HUDConfig instance() {
        return HANDLER.instance();
    }

    public static Screen getConfigScreen(Screen parent) {
        load();
        return HANDLER.generateGui().generateScreen(parent);
    }
}
