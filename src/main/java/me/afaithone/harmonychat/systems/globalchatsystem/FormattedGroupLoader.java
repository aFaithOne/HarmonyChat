package me.afaithone.harmonychat.systems.globalchatsystem;

import me.afaithone.harmonychat.PluginController;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;
import java.util.Set;

public class FormattedGroupLoader {

    private final PluginController controller;
    private final ChatManager chatManager;

    public FormattedGroupLoader(ChatManager chatManager, PluginController controller) {
        this.chatManager = chatManager;
        this.controller = controller;
    }

    public void load() {
        FileConfiguration config = controller.getMainConfig().getConfig();

        Set<String> keys = Objects.requireNonNull(config.getConfigurationSection("globalChatFormat.groups")).getKeys(false);
        FormattedGroupsHolder formattedGroupsHolder = chatManager.getFormattedGroupsHolder();

        for (String key : keys) {
            formattedGroupsHolder.getFormattedGroupMap()
                    .add(key, createFormattedGroup(config, key));
        }
    }

    private FormattedGroup createFormattedGroup(FileConfiguration config, String key) {
        return new FormattedGroup(config.getString("globalChatFormat.groups." +key));
    }
}
