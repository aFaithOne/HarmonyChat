package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
import me.lumenowaty.harmonychat.utils.ChatUtils;
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
