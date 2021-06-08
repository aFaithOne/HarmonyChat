package me.lumenowaty.harmonychat.components.interfaces;

import me.lumenowaty.harmonychat.MessagesController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

    boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages);
}
