package dev.ujol.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.*;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import dev.ujol.HUDDisplayClient;
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

    @AutoGen(category = "general")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public boolean enabled = true;

    @AutoGen(category = "general", group = "equipments")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public boolean equipmentsEnabled = true;
    @AutoGen(category = "general", group = "equipments")
    @IntField
    @SerialEntry
    public int equipmentsXOffset = 0;
    @AutoGen(category = "general", group = "equipments")
    @IntField
    @SerialEntry
    public int equipmentsYOffset = 150;
    @AutoGen(category = "general", group = "equipments")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color equipmentsTextColor = Color.white;
    @AutoGen(category = "general", group = "equipments")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color equipmentsBgColor = new Color(0x80000000, true);

    @AutoGen(category = "general", group = "info")
    @Boolean(formatter = Boolean.Formatter.ON_OFF, colored = true)
    @SerialEntry
    public boolean infoEnabled = true;
    @AutoGen(category = "general", group = "info")
    @IntField
    @SerialEntry
    public int infoXOffset = 0;
    @AutoGen(category = "general", group = "info")
    @IntField
    @SerialEntry
    public int infoYOffset = 0;
    @AutoGen(category = "general", group = "info")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color infoTextColor = Color.white;
    @AutoGen(category = "general", group = "info")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color infoBgColor = new Color(0x80000000, true);

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