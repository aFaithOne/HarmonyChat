package me.afaithone.harmonychat.systems.globalchatsystem;

import me.afaithone.harmonychat.utils.ChatUtils;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.PluginController;
import me.afaithone.harmonychat.components.HCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class BroadcastCommand extends HCommandExecutor {

    public BroadcastCommand(PluginController controller) {
        super(controller);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesController messages = this.controller.getMessagesController();

        if (args.length == 0) {
            sender.sendMessage(messages.chatBroadcastUsage());
            return false;
        }

        String message = ChatUtils.buildMessage(args, 0, args.length);

        Bukkit.broadcastMessage(messages.chatBroadcast(message));
        return true;
    }
}
