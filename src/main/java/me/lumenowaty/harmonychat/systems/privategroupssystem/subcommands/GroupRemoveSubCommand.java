package me.lumenowaty.harmonychat.systems.privategroupssystem.subcommands;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.privategroupssystem.GroupCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class GroupRemoveSubCommand extends HSubCommand<GroupCommand> {

    public GroupRemoveSubCommand(GroupCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        return false;
    }
}
