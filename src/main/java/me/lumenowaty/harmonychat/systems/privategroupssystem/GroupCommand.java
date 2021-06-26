package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
import me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands.*;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class GroupCommand extends HCommandExecutor implements TabCompleter {

    public GroupCommand(PluginController controller) {
        super(controller);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesController messages = controller.getMessagesController();

        if (! (sender instanceof Player)) {
            sender.sendMessage(messages.inGameCommand());
            return false;
        }

        Player actor = (Player) sender;

        if (! actor.hasPermission("harmony.chat.groups")) {
            actor.sendMessage(messages.noPermission());
            return false;
        }

        if (args.length == 0) {
            actor.sendMessage(messages.privateGroupUsage());
            return false;
        }

        switch (args[0]) {
            case "create":
                return new GroupCreateSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "delete":
                return new GroupDeleteSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "join":
                return new GroupJoinSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "leave":
                return new GroupLeaveSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "invite":
                return new GroupInviteSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "remove":
                return new GroupRemoveSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "asadmin":
                return new GroupAsAdminSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "info":
                return new GroupInfoSubCommand(this).onSubCommand(sender, command, label, args, messages);
            default:
                sendMessageToGroup(actor, ChatUtils.buildMessage(args, 0, args.length));
                break;
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return Arrays.asList("create", "delete", "join", "leave", "invite", "remove", "info");
        return null;
    }

    public static void sendMessageToGroup(Player actor, String message) {
        PluginController controller = HarmonyChat.getController();
        MessagesController messages = controller.getMessagesController();
        SocialGroupManager socialGroupManager = controller.getSocialGroupManager();
        Optional<SocialGroup> socialGroupOfPlayer = socialGroupManager.getSocialGroupOfPlayer(actor);

        if (! socialGroupOfPlayer.isPresent()) {
            actor.sendMessage(messages.privateGroupsNoGroup());
            return;
        }

        List<UUID> list = socialGroupOfPlayer.get().getGroupMembers().getList();

        List<UUID> collect = list.stream().filter(s -> {
            Player player = Bukkit.getPlayer(s);

            if (player != null) return player.isOnline();
            return false;
        }).collect(Collectors.toList());

        if (collect.size() == 1) {
            actor.sendMessage(messages.privateGroupsNoReceivers());
            return;
        }

        collect.forEach(s -> {
            Player player = Bukkit.getPlayer(s);

            if (player != null)
                messages.privateGroupsPrefixReceive(player, message);
        });

        actor.sendMessage(messages.privateGroupsPrefixSend(message));
    }
}
