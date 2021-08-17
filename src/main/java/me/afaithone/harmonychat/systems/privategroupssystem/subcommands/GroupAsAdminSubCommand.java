package me.afaithone.harmonychat.systems.privategroupssystem.subcommands;

import me.afaithone.harmonychat.systems.privategroupssystem.adminsubcommands.GroupAsAdminListSubCommand;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.GroupCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.adminsubcommands.GroupAsAdminDeleteSubCommand;
import me.afaithone.harmonychat.systems.privategroupssystem.adminsubcommands.GroupAsAdminInfoSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class GroupAsAdminSubCommand extends HSubCommand<GroupCommand> {

    public GroupAsAdminSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {

        if (! sender.hasPermission("harmony.chat.moderate")) {
            sender.sendMessage(messages.noPermission());
            return false;
        }
        
        if (args.length < 2) {
            sender.sendMessage(messages.privateGroupsAsAdminUsage());
            return false;
        }

        switch(args[1]) {
            case "list":
                return new GroupAsAdminListSubCommand(commandExecutor).onSubCommand(sender, command, label, args, messages);
            case "delete":
                return new GroupAsAdminDeleteSubCommand(commandExecutor).onSubCommand(sender, command, label, args, messages);
            case "info":
                return new GroupAsAdminInfoSubCommand(commandExecutor).onSubCommand(sender, command, label, args, messages);
            default:
                sender.sendMessage(messages.privateGroupsAsAdminUsage());
                return false;
        }
    }
}
