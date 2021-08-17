package me.afaithone.harmonychat.systems.privategroupssystem.subcommands;

import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.components.HMap;
import me.afaithone.harmonychat.systems.privategroupssystem.*;
import me.afaithone.harmonychat.systems.privategroupssystem.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class GroupCreateSubCommand extends HSubCommand<GroupCommand> {

    public GroupCreateSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        Player actor = (Player) sender;
        SocialGroupManager socialGroupManager = HarmonyChat.getController().getSocialGroupManager();
        SocialGroupsHolder holder = socialGroupManager.getSocialGroupsHolder();
        HMap<UUID, SocialGroup> socialGroups = holder.getSocialGroups();
        UUID actorId = actor.getUniqueId();

        if (socialGroupManager.isPlayerOwnerOfGroup(actor) || socialGroupManager.isPlayerInGroup(actor)) {
            actor.sendMessage(messages.privateGroupsMemberAlready());
            return false;
        }

        AtomicBoolean contains = new AtomicBoolean(false);

        socialGroups.getMap().forEach( (s, m) -> {
            if (m.getGroupMembers().contains(actorId)) contains.set(true);
        });

        if (contains.get()) {
            actor.sendMessage(messages.privateGroupsMemberAlready());
            return false;
        }

        socialGroups.add(actorId, SocialGroupFactory.create(actorId));
        actor.sendMessage(messages.privateGroupsCreated());
        return true;
    }
}
