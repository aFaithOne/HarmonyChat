package me.lumenowaty.harmonychat;

import org.bukkit.plugin.java.JavaPlugin;

public final class HarmonyChat extends JavaPlugin {

    private static HarmonyChat harmonyChat;
    private static PluginController controller;

    @Override
    public void onEnable() {
        harmonyChat = this;

        controller = new PluginController(harmonyChat);
        controller.loadComponents();
        controller.runComponents();
        controller.loadCommands();
        controller.loadTabCompleters();
        controller.loadEvents();
    }

    @Override
    public void onDisable() {
        controller.saveComponents();
    }

    public static HarmonyChat getInstance() {
        return harmonyChat;
    }

    public static PluginController getController() {
        return controller;
    }
}
