package me.lumenowaty.harmonychat;

import me.lumenowaty.harmonychat.utils.ChatUtils;
import me.lumenowaty.harmonychat.utils.YamlConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class MessagesController {

    private final YamlConfig config;

    private FileConfiguration messages;

    public MessagesController() {
        this.config = new YamlConfig(HarmonyChat.getInstance().getDataFolder(), "messages");

        initialize();
    }

    private void initialize() {
        this.reload();
    }

    private void loadConfigClass() {
        this.config.createConfig();
        this.config.reload();
        this.messages = config.getConfig();
    }

    public void reload() {
        this.loadConfigClass();
    }

    private String formatMessage(String path) {
        return ChatUtils.formatText(messages.getString(path));
    }

    private List<String> formatMessageAsList(String path) {
        return ChatUtils.formatText(messages.getStringList(path));
    }

    private String formatMessageList(String path) {
        List<String> message = ChatUtils.formatText(messages.getStringList(path));
        StringBuilder builder = new StringBuilder();

        for(String s : message) {
            builder.append(s).append("\n");
        }
        return builder.toString();
    }

    /**
     Messages from Global
     */

    public String inGameCommand() {
        return formatMessage("global.inGameCommand");
    }

    public String noPermission() {
        return formatMessage("global.noPermission");
    }

    public String offlinePlayer() {
        return formatMessage("global.offlinePlayer");
    }

    /**
     Messages from Blocked
     */

    public String privateMessageIgnored(String target) {
        return formatMessage("privateMessage.ignored")
                .replaceAll("%target%", target);
    }

    /**
     Messages from AntiSpamController
     */

    public String messageCoolDown(int seconds) {
        return formatMessage("messagesAntiSpam.cooldown")
                .replaceAll("%time%", String.valueOf(seconds));
    }

    /**
        Messages from PrivateMessageCommand
     */

    public String privateMessageUsage() {
        return formatMessage("privateMessage.usage");
    }

    public String privateMessageSend(String target, String message) {
        return formatMessage("privateMessage.send")
                .replaceAll("%target%", target)
                .replaceAll("%message%", message);
    }

    public String privateMessageReceive(String actor, String message) {
        return formatMessage("privateMessage.receive")
                .replaceAll("%actor%", actor)
                .replaceAll("%message%", message);
    }

  //  public String privateMessageIgnored();

    /**
     Messages from IgnoreMessageCommand
     */

    public String ignoreUsage() {
        return formatMessage("ignore.usage");
    }

    public String ignoreAdded(String target) {
        return formatMessage("ignore.added")
                .replaceAll("%target%", target);
    }

    public String ignoreRemoved(String target) {
        return formatMessage("ignore.removed")
                .replaceAll("%target%", target);
    }

    public String ignoreList(String list) {
        return formatMessage("ignore.list") + list;
    }

    public String ignoreNotAllowed() {
        return formatMessage("ignore.notAllowed");
    }

    /**
     Messages from ReplyMessageCommand
     */

    public String replyUsage() {
        return formatMessage("reply.usage");
    }

    public String replyOfflinePlayer() {
        return formatMessage("reply.offlinePlayer");
    }

    public String replyNoPlayerToReply() {
        return formatMessage("reply.noPlayerToReply");
    }

    /**
     Messages from Chat
     */

    public String chatUsage() {
        return formatMessage("chat.usage");
    }

    public String chatClearCleared() {
        return formatMessageList("chat.cleared");
    }

    public String chatOffUsage() {
        return formatMessage("chat.offUsage");
    }

    public String chatOffDisabled(String description) {
        return formatMessageList("chat.disabled")
                .replaceAll("%description%", description);
    }

    public String chatOffDisable(String description) {
        return formatMessage("chat.disable")
                .replaceAll("%description%", description);
    }

    public String chatOnEnabled() {
        return formatMessageList("chat.enabled");
    }

    public String chatBroadcast(String message) {
        return formatMessageList("chat.broadcast")
                .replaceAll("%message%", message);
    }

    public String chatBroadcastUsage() {
        return formatMessage("chat.broadcastUsage");
    }

    public String chatReloaded() {
        return formatMessage("chat.reloaded");
    }

    /**
     Messages from AntiSpam
     */

    public String antiSpamCoolDown(String time) {
        return formatMessage("antiSpam.timeout")
                .replaceAll("%time%", time);
    }
}

