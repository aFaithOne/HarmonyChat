package me.afaithone.harmonychat.systems.privategroupssystem.subcommands;

import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.systems.privategroupssystem.GroupCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.SocialGroupManager;
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
