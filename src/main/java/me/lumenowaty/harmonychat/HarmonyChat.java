package me.lumenowaty.harmonychat;

import org.bukkit.plugin.java.JavaPlugin;

public final class HarmonyChat extends JavaPlugin {

    private static HarmonyChat harmonyChat;
    private static PluginController controller;

    @Override
    public void onEnable() {
        harmonyChat = this;

        controller = new PluginController(harmonyChat);
        controller.runPlugin();
    }

    @Override
    public void onDisable() {
        controller.saveComponents();
        controller.stopComponents();
    }

    public static HarmonyChat getInstance() {
        return harmonyChat;
    }

    public static PluginController getController() {
        return controller;
    }
}
