package me.lumenowaty.harmonychat.systems.automessagessystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HController;
import me.lumenowaty.harmonychat.utils.YamlConfig;
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
