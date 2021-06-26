package me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HStatus;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.ChatCommand;
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
