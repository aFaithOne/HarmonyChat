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

public class GroupInfoSubCommand extends HSubCommand<GroupCommand> {

    public GroupInfoSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        Player actor = (Player) sender;
        SocialGroupManager socialGroupManager = HarmonyChat.getController().getSocialGroupManager();
        Optional<SocialGroup> containingActorGroup = socialGroupManager.getSocialGroupOfPlayer(actor);

        if (! containingActorGroup.isPresent()) {
            actor.sendMessage(messages.privateGroupsNoGroup());
            return false;
        }

        SocialGroup socialGroup = containingActorGroup.get();
        actor.sendMessage(messages.privateGroupsInfo(socialGroup));
        return true;
    }
}
