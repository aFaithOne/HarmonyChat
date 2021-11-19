package me.lumenowaty.harmonychat.systems.privategroupssystem.adminsubcommands;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HMap;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroup;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroupManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class GroupAsAdminDeleteSubCommand extends HSubCommand<GroupCommand> {

    public GroupAsAdminDeleteSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        SocialGroupManager manager = HarmonyChat.getController().getSocialGroupManager();
        HMap<UUID, SocialGroup> socialGroups = manager.getSocialGroupsHolder().getSocialGroups();

        if (args.length < 3) {
            sender.sendMessage(messages.privateGroupsAsAdminDeleteUsage());
            return false;
        }

        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[2]);
        UUID uniqueId = offlinePlayer.getUniqueId();

        if (! socialGroups.containsKey(uniqueId)) {
            sender.sendMessage(messages.privateGroupInvalidGroup());
            return false;
        }

        sender.sendMessage(messages.privateGroupsAsAdminDeleted());
        socialGroups.remove(uniqueId);
        return true;
    }
}
