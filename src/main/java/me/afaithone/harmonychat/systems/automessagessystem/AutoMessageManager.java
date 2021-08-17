package me.afaithone.harmonychat.systems.automessagessystem;

import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.utils.YamlConfig;
import me.afaithone.harmonychat.PluginController;
import me.afaithone.harmonychat.components.HController;
import org.bukkit.configuration.file.FileConfiguration;

public class AutoMessageManager extends HController {

    private final AutoMessageHolder messagesHolder;
    private final YamlConfig autoMessagesConfig;

    private AutoMessageTask autoMessageTask;

    public AutoMessageManager(PluginController controller) {
        super(controller);
        this.messagesHolder = new AutoMessageHolder();
        this.autoMessagesConfig = new YamlConfig(HarmonyChat.getInstance().getDataFolder(), "autoMessages");

        this.loadAutoMessageConfig();

        this.prepareAutoMessageTask();
    }

    private void loadAutoMessageConfig() {
        this.autoMessagesConfig.createConfig();
        this.autoMessagesConfig.reload();
    }

    private void prepareAutoMessageTask() {
        FileConfiguration mainConfig = this.controller.getMainConfig().getConfig();

        this.messagesHolder.getMessages()
                .setList(autoMessagesConfig.getConfig().getStringList("messages"));

        int period = mainConfig.getInt("autoMessages.interval");
        int displayTime = mainConfig.getInt("autoMessages.displayTime");
        int delayed = 0;

        this.autoMessageTask = new AutoMessageTask(period, delayed, displayTime, this);
    }

    public AutoMessageHolder getMessagesHolder() {
        return this.messagesHolder;
    }

    public AutoMessageTask getAutoMessageTask() {
        return this.autoMessageTask;
    }
}
