package me.afaithone.harmonychat;

import me.afaithone.harmonychat.utils.ChatUtils;
import me.afaithone.harmonychat.utils.YamlConfig;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroup;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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

    /**
     Messages from PrivateGroups
     */

    public String privateGroupUsage() {
        return formatMessage("privateGroup.usage");
    }

    public String privateGroupsMemberAlready() {
        return formatMessage("privateGroup.alreadyAMember");
    }

    public String privateGroupsCreated() {
        return formatMessage("privateGroup.created");
    }

    public String privateGroupsOwnerCommand() {
        return formatMessage("privateGroup.ownerCommand");
    }

    public String privateGroupsDeleted() {
        return formatMessage("privateGroup.deleted");
    }

    public String privateGroupsLeft() {
        return formatMessage("privateGroup.left");
    }

    public String privateGroupsNotForOwner() {
        return formatMessage("privateGroup.notForOwner");
    }

    public String privateGroupsNoGroup() {
        return formatMessage("privateGroup.noGroup");
    }

    public String privateGroupsInviteUsage() {
        return formatMessage("privateGroup.inviteUsage");
    }

    public String privateGroupsInviteSend() {
        return formatMessage("privateGroup.inviteSend");
    }

    public String privateGroupsInviteReceived() {
        return formatMessage("privateGroup.inviteReceived");
    }

    public String privateGroupsNoInvitation() {
        return formatMessage("privateGroup.noInvitation");
    }

    public String privateGroupsJoined() {
        return formatMessage("privateGroup.joined");
    }

    public String privateGroupsNotExistingNow() {
        return formatMessage("privateGroup.notExistingNow");
    }

    public String privateGroupsRemoveUsage() {
        return formatMessage("privateGroup.removeUsage");
    }

    public String privateGroupsRemoved() {
        return formatMessage("privateGroup.removed");
    }

    public String privateGroupsKicked() {
        return formatMessage("privateGroup.kicked");
    }

    public String privateGroupInvalidGroup() {
        return formatMessage("privateGroup.invalidGroup");
    }

    public String privateGroupsPrefixReceive(Player actor, String message) {
        return formatMessage("privateGroup.prefixReceive")
                .replaceAll("%message%", message)
                .replaceAll("%actor%", actor.getDisplayName());
    }

    public String privateGroupsPrefixSend(String message) {
        return formatMessage("privateGroup.prefixSend")
                .replaceAll("%message%", message);
    }

    public String privateGroupsNoReceivers() {
        return formatMessage("privateGroup.noReceivers");
    }

    public String privateGroupsAsAdminUsage() {
        return formatMessage("privateGroup.asAdminUsage");
    }

    public String privateGroupsList() {
        return formatMessage("privateGroup.groupsList");
    }

    public String privateGroupsAsAdminDeleteUsage() {
        return formatMessage("privateGroup.asAdminDeleteUsage");
    }

    public String privateGroupsAsAdminDeleted() {
        return formatMessage("privateGroup.asAdminDeleted");
    }

    public String privateGroupsInfo(SocialGroup socialGroup) {
        UUID groupAdmin = socialGroup.getGroupAdmin();
        return formatMessageList("privateGroup.info")
                .replaceAll("%id", String.valueOf(groupAdmin))
                .replaceAll("%actor%", Objects.requireNonNull(Bukkit.getOfflinePlayer(groupAdmin).getName()))
                .replaceAll("%members%", socialGroup.getGroupMembers().getList().stream()
                        .map(s -> Bukkit.getOfflinePlayer(s).getName() + ", ").collect(Collectors.joining()));
    }

    public String privateGroupsInviteReceivedHover() {
        return formatMessage("privateGroup.inviteReceivedHover");
    }

    public String privateGroupsSocialGroupChatEnabled() {
        return formatMessage("privateGroup.socialGroupChatEnabled");
    }

    public String privateGroupsSocialGroupChatDisabled() {
        return formatMessage("privateGroup.socialGroupChatDisabled");
    }
}

