package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
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
            return false;
        }
        return false;
    }
}
