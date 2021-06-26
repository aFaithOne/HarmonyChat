package me.lumenowaty.harmonychat;

import me.lumenowaty.harmonychat.systems.antispamsystem.AntiSpamManager;
import me.lumenowaty.harmonychat.systems.automessagessystem.AutoMessageManager;
import me.lumenowaty.harmonychat.systems.globalchatsystem.BroadcastCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.ChatCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.ChatListener;
import me.lumenowaty.harmonychat.systems.globalchatsystem.ChatManager;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroupManager;
import me.lumenowaty.harmonychat.systems.privatemessagessystem.IgnorePlayerCommand;
import me.lumenowaty.harmonychat.systems.privatemessagessystem.PrivateMessageCommand;
import me.lumenowaty.harmonychat.systems.privatemessagessystem.PrivateMessageManager;
import me.lumenowaty.harmonychat.systems.privatemessagessystem.ReplyMessageCommand;
import me.lumenowaty.harmonychat.utils.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class PluginController {

    public static final String HARMONY_CHAT_PREFIX = "[HarmonyChat]";

    private final HarmonyChat main;
    private final YamlConfig mainConfig;
    private final YamlConfig messagesConfig;

    final private MessagesController messagesController;
    private AutoMessageManager autoMessagesManager;
    private PrivateMessageManager privateMessageManager;
    private ChatManager chatManager;
    private AntiSpamManager antiSpamManager;
    private SocialGroupManager socialGroupManager;

    public PluginController(HarmonyChat main) {
        this.main = main;
        this.mainConfig = new YamlConfig(main.getDataFolder(), "config");
        this.messagesConfig = new YamlConfig(main.getDataFolder(), "messages");
        this.messagesController = new MessagesController();

        this.loadMainConfig();
        this.loadMessagesConfig();
    }

    private void loadMainConfig() {
        this.mainConfig.createConfig();
        this.mainConfig.reload();
    }

    private void loadMessagesConfig() {
        this.messagesConfig.createConfig();
        this.messagesConfig.reload();
    }

    private void loadCommands() {
        this.main.getCommand("privateMessage").setExecutor(new PrivateMessageCommand(this));
        this.main.getCommand("ignore").setExecutor(new IgnorePlayerCommand(this));
        this.main.getCommand("reply").setExecutor(new ReplyMessageCommand(this));
        this.main.getCommand("chat").setExecutor(new ChatCommand(this));
        this.main.getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        this.main.getCommand("group").setExecutor(new GroupCommand(this));
    }

    private void loadTabCompleters() {
        this.main.getCommand("chat").setTabCompleter(new ChatCommand(this));
    }

    private void loadEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChatListener(this), this.main);
    }

    private void loadComponents() {
        this.autoMessagesManager = new AutoMessageManager(this);

        this.privateMessageManager = new PrivateMessageManager(this);

        this.chatManager = new ChatManager(this);

        this.antiSpamManager = new AntiSpamManager(this);

        this.socialGroupManager = new SocialGroupManager(this);
    }

    public void reloadPlugin() {
        this.stopComponents();

        this.loadMainConfig();
        this.loadMessagesConfig();

        this.autoMessagesManager = null;
        this.privateMessageManager = null;
        this.chatManager = null;
        this.antiSpamManager = null;

        this.loadComponents();
        this.runComponents();
    }

    public void runPlugin() {
        this.loadComponents();
        this.runComponents();
        this.loadCommands();
        this.loadTabCompleters();
        this.loadEvents();
    }

    public void saveComponents() {
        this.privateMessageManager.saveComponents();
        this.socialGroupManager.saveComponents();
    }

    private void runComponents() {
        this.autoMessagesManager.getAutoMessageTask().run();
        this.privateMessageManager.loadComponents();
        this.chatManager.loadComponents();
        this.antiSpamManager.load();
        this.socialGroupManager.loadComponents();
    }

    public void stopComponents() {
        this.autoMessagesManager.getAutoMessageTask().stop();
        this.autoMessagesManager.getAutoMessageTask().clear();
    }

    public YamlConfig getMainConfig() {
        return mainConfig;
    }

    public YamlConfig getMessagesConfig() {
        return messagesConfig;
    }

    public MessagesController getMessagesController() {
        return messagesController;
    }

    public AutoMessageManager getAutoMessagesManager() {
        return autoMessagesManager;
    }

    public PrivateMessageManager getPrivateMessageManager() {
        return privateMessageManager;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }

    public AntiSpamManager getAntiSpamManager() {
        return antiSpamManager;
    }

    public SocialGroupManager getSocialGroupManager() {
        return socialGroupManager;
    }
}
