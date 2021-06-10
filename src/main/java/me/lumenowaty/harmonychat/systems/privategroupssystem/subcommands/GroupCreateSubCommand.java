package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupMessageCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class GroupCreateSubCommand extends HSubCommand<GroupMessageCommand> {

    public GroupCreateSubCommand(GroupMessageCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        return false;
    }
}
