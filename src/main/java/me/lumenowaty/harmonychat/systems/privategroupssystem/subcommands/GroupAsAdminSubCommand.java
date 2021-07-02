package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.adminsubcommands.GroupAsAdminDeleteSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.adminsubcommands.GroupAsAdminInfoSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.adminsubcommands.GroupAsAdminListSubCommand;
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
