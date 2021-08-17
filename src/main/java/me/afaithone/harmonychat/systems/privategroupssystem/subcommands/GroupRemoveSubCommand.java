package me.afaithone.harmonychat.systems.privategroupssystem.subcommands;

import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.utils.HarmonyUtils;
import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.systems.privategroupssystem.GroupCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroup;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class GroupRemoveSubCommand extends HSubCommand<GroupCommand> {

    public GroupRemoveSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        Player actor = (Player) sender;
        SocialGroupManager socialGroupManager = HarmonyChat.getController().getSocialGroupManager();

        if (! socialGroupManager.isPlayerOwnerOfGroup(actor)) {
            actor.sendMessage(messages.privateGroupsOwnerCommand());
            return false;
        }

        if (args.length < 2) {
            actor.sendMessage(messages.privateGroupsRemoveUsage());
            return false;
        }

        Optional<Player> playerOptional = HarmonyUtils.getPlayerByName(args[1]);

        if (! playerOptional.isPresent()) {
            actor.sendMessage(messages.offlinePlayer());
            return false;
        }

        Player target = playerOptional.get();
        Optional<SocialGroup> byKey = socialGroupManager.getSocialGroupsHolder().getSocialGroups().getByKey(actor.getUniqueId());

        byKey.ifPresent(s -> {
         s.removeMember(target.getUniqueId());
         actor.sendMessage(messages.privateGroupsRemoved());
         target.sendMessage(messages.privateGroupsKicked());
        });
        return true;
    }
}
