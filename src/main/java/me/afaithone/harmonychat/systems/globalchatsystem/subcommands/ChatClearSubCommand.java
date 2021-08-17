package me.afaithone.harmonychat.systems.globalchatsystem.subcommands;

import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.systems.globalchatsystem.ChatCommand;
import me.afaithone.harmonychat.MessagesController;
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
