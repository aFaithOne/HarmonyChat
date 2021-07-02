package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HMap;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.*;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import me.lumenowaty.harmonychat.utils.HarmonyUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class GroupInviteSubCommand extends HSubCommand<GroupCommand> {

    public GroupInviteSubCommand(GroupCommand commandExecutor) {
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
            actor.sendMessage(messages.privateGroupsInviteUsage());
            return false;
        }

        Optional<Player> playerOptional = HarmonyUtils.getPlayerByName(args[1]);

        if (! playerOptional.isPresent()) {
            actor.sendMessage(messages.offlinePlayer());
            return false;
        }

        Player target = playerOptional.get();
        HMap<UUID, UUID> invitationMap = socialGroupManager.getInvitationHolder().getInvitationMap();
        TextComponent g_join = ChatUtils.createPerformingCommandText(messages.privateGroupsInviteReceived(), messages.privateGroupsInviteReceivedHover(), "/group join");

        actor.sendMessage(messages.privateGroupsInviteSend());
        target.spigot().sendMessage(g_join);
        Bukkit.getScheduler().scheduleSyncDelayedTask(HarmonyChat.getInstance(),() -> invitationMap.remove(target.getUniqueId()),  20L*30);
        invitationMap.put(target.getUniqueId(), actor.getUniqueId());
        return true;
    }
}
