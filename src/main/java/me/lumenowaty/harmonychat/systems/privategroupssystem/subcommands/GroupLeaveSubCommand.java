package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroup;
import me.lumenowaty.harmonychat.systems.privategroupssystem.SocialGroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class GroupLeaveSubCommand extends HSubCommand<GroupCommand> {

    public GroupLeaveSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        Player actor = (Player) sender;
        SocialGroupManager socialGroupManager = HarmonyChat.getController().getSocialGroupManager();
        UUID actorId = actor.getUniqueId();

        if (socialGroupManager.isPlayerOwnerOfGroup(actor)) {
            actor.sendMessage(messages.privateGroupsNotForOwner());
            return false;
        }

        Optional<SocialGroup> containingActorGroup = socialGroupManager.getSocialGroupOfPlayer(actor);

        if (! containingActorGroup.isPresent()) {
            actor.sendMessage(messages.privateGroupsNoGroup());
            return false;
        }

        actor.sendMessage(messages.privateGroupsLeft());
        containingActorGroup.get().removeMember(actorId);
        return true;
    }
}
