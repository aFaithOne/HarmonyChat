package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
import me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupMessageCommand extends HCommandExecutor {

    public GroupMessageCommand(PluginController controller) {
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
            default:
                sender.sendMessage(messages.chatUsage());
                break;
        }
        return false;
    }
}
