package me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.ChatCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ChatClearSubCommand extends HSubCommand<ChatCommand> {

    public ChatClearSubCommand(ChatCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
            for (int i = 0; i < 100; i++) {
                Bukkit.broadcastMessage(" ");
            }
            Bukkit.broadcastMessage(messages.chatClearCleared());
        return true;
    }
}
