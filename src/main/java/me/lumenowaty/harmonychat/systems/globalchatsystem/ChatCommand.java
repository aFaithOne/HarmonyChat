package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
import me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands.ChatClearSubCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands.ChatOffSubCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands.ChatOnSubCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands.ChatReloadSubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class ChatCommand extends HCommandExecutor implements TabCompleter {


    public ChatCommand(PluginController controller) {
        super(controller);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesController messages = controller.getMessagesController();

        if (! sender.hasPermission("harmony.chat.manage")) {
            sender.sendMessage(messages.noPermission());
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(messages.chatUsage());
            return false;
        }

        switch (args[0]) {
            case "clear":
                return new ChatClearSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "off":
                return new ChatOffSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "on":
                return new ChatOnSubCommand(this).onSubCommand(sender, command, label, args, messages);
            case "reload":
                return new ChatReloadSubCommand(this).onSubCommand(sender, command, label, args, messages);
            default:
                sender.sendMessage(messages.chatUsage());
                break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) return Arrays.asList("clear", "off", "on", "reload");
        return null;
    }
}
