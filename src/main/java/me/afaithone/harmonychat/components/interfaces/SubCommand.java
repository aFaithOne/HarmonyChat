package me.afaithone.harmonychat.components.interfaces;

import me.afaithone.harmonychat.MessagesController;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

    boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages);
}
