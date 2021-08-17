package me.afaithone.harmonychat.systems.globalchatsystem.subcommands;

import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.components.HStatus;
import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.systems.globalchatsystem.ChatCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ChatOnSubCommand extends HSubCommand<ChatCommand> {

    public ChatOnSubCommand(ChatCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        HStatus chatStatus = HarmonyChat.getController().getChatManager().getChatStatus();

        chatStatus.setStatus(true);
        chatStatus.setDescription("");

        Bukkit.broadcastMessage(messages.chatOnEnabled());
        return false;
    }
}
