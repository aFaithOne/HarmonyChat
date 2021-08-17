package me.afaithone.harmonychat.systems.privategroupssystem.adminsubcommands;

import me.afaithone.harmonychat.utils.ChatUtils;
import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.components.HMap;
import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.GroupCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroup;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GroupAsAdminListSubCommand extends HSubCommand<GroupCommand> {

    public GroupAsAdminListSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        SocialGroupManager manager = HarmonyChat.getController().getSocialGroupManager();
        HMap<UUID, SocialGroup> socialGroups = manager.getSocialGroupsHolder().getSocialGroups();
        Map<UUID, SocialGroup> map = socialGroups.getMap();
        Set<UUID> uuids = map.keySet();
        StringBuilder builder = new StringBuilder();

        builder.append(messages.privateGroupsList());
        uuids.forEach(s -> {
            SocialGroup socialGroup = map.get(s);
            UUID groupAdmin = socialGroup.getGroupAdmin();
            builder
                    .append("\n")
                    .append("&7")
                    .append(groupAdmin)
                    .append(" &6: &7")
                    .append(Bukkit.getOfflinePlayer(groupAdmin).getName());
        });
        sender.sendMessage(ChatUtils.formatText(builder.toString()));
        return true;
    }
}
