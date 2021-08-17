package me.afaithone.harmonychat.systems.privategroupssystem.adminsubcommands;

import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.components.HMap;
import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.GroupCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroup;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroupManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Optional;
import java.util.UUID;

public class GroupAsAdminInfoSubCommand extends HSubCommand<GroupCommand> {

    public GroupAsAdminInfoSubCommand(GroupCommand commandExecutor) {
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
        Optional<SocialGroup> byKey = socialGroups.getByKey(uniqueId);

        if (! byKey.isPresent()) {
            sender.sendMessage(messages.privateGroupInvalidGroup());
            return false;
        }

        sender.sendMessage(messages.privateGroupsInfo(byKey.get()));
        socialGroups.remove(uniqueId);
        return true;
    }
}
