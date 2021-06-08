package me.lumenowaty.harmonychat.systems.antispamsystem;

import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HController;
import org.bukkit.configuration.file.FileConfiguration;

public class AntiSpamManager extends HController {

    private final AntiSpamHolder antiSpamHolder;

    private int messageTimeout;

    public AntiSpamManager(PluginController controller) {
        super(controller);
        this.antiSpamHolder = new AntiSpamHolder();
    }

    public void load() {
        FileConfiguration config = controller.getMainConfig().getConfig();

        this.messageTimeout = config.getInt("antiSpam.cooldown");
    }

    public AntiSpamHolder getAntiSpamHolder() {
        return antiSpamHolder;
    }

    public int getMessageTimeout() {
        return this.messageTimeout;
    }

    public void setMessageTimeout(int messageTimeout) {
        this.messageTimeout = messageTimeout;
    }
}
