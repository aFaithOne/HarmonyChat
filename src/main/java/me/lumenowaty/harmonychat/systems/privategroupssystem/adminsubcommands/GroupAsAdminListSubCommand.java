package me.lumenowaty.harmonychat.systems.privategroupssystem.adminsubcommands;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HMap;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroup;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroupManager;
import me.lumenowaty.harmonychat.utils.ChatUtils;
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
