package me.lumenowaty.harmonychat.systems.globalchatsystem.subcommands;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.components.HStatus;
import me.lumenowaty.harmonychat.components.HSubCommand;
import me.lumenowaty.harmonychat.systems.globalchatsystem.ChatCommand;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ChatOffSubCommand extends HSubCommand<ChatCommand> {

    public ChatOffSubCommand(ChatCommand commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        if (args.length < 2) {
            sender.sendMessage(messages.chatOffUsage());
            return false;
        }

        String description = ChatUtils.buildMessage(args, 1, args.length);
        HStatus chatStatus = HarmonyChat.getController().getChatManager().getChatStatus();

        chatStatus.setStatus(false);
        chatStatus.setDescription(description);

        Bukkit.broadcastMessage(messages.chatOffDisabled(description));
        return false;
    }
}
