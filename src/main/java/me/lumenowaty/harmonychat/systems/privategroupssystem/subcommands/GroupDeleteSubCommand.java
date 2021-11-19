package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GroupDeleteSubCommand extends HSubCommand<GroupCommand> {

    public GroupDeleteSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        Player actor = (Player) sender;
        SocialGroupManager socialGroupManager = HarmonyChat.getController().getSocialGroupManager();
        UUID actorId = actor.getUniqueId();

        if (! socialGroupManager.isPlayerOwnerOfGroup(actor)) {
            actor.sendMessage(messages.privateGroupsOwnerCommand());
            return false;
        }

        socialGroupManager.getSocialGroupsHolder().getSocialGroups().remove(actorId);
        actor.sendMessage(messages.privateGroupsDeleted());
        return true;
    }
}
