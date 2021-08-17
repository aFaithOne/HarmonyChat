package me.afaithone.harmonychat.systems.privategroupssystem.subcommands;

import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.components.HMap;
import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;


public class GroupJoinSubCommand extends HSubCommand<GroupCommand> {

    public GroupJoinSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        Player actor = (Player) sender;
        SocialGroupManager socialGroupManager = HarmonyChat.getController().getSocialGroupManager();

        if (socialGroupManager.isPlayerInGroup(actor)) {
            actor.sendMessage(messages.privateGroupsMemberAlready());
            return false;
        }

        GroupInvitationHolder invitationHolder = socialGroupManager.getInvitationHolder();
        HMap<UUID, UUID> invitationMap = invitationHolder.getInvitationMap();
        UUID actorId = actor.getUniqueId();

        if (! invitationMap.containsKey(actorId)) {
            actor.sendMessage(messages.privateGroupsNoInvitation());
            return false;
        }

        SocialGroupsHolder socialGroupsHolder = socialGroupManager.getSocialGroupsHolder();
        HMap<UUID, SocialGroup> socialGroups = socialGroupsHolder.getSocialGroups();
        Optional<UUID> byKey = invitationMap.getByKey(actorId);

        if (! byKey.isPresent()) {
            actor.sendMessage(messages.privateGroupsNoInvitation());
            return false;
        }

        Optional<SocialGroup> sGByKey = socialGroups.getByKey(byKey.get());

        if (! sGByKey.isPresent()) {
            actor.sendMessage(messages.privateGroupsNotExistingNow());
            return false;
        }

        actor.sendMessage(messages.privateGroupsJoined());
        sGByKey.get().addMember(actorId);
        return true;
    }
}