package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.utils.HarmonyUtils;
import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroup;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroupManager;
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
