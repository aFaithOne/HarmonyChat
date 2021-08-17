package me.afaithone.harmonychat.systems.globalchatsystem.subcommands;

import me.afaithone.harmonychat.components.HCommandExecutor;
import me.afaithone.harmonychat.components.HSubCommand;
import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.MessagesController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ChatReloadSubCommand extends HSubCommand {

    public ChatReloadSubCommand(HCommandExecutor commandExecutor) {
        super(commandExecutor);
    }

    @Override
    public boolean onSubCommand(CommandSender sender, Command command, String label, String[] args, MessagesController messages) {
        if (! sender.hasPermission("harmony.chat.reload")) {
            sender.sendMessage(messages.noPermission());
            return false;
        }

        Bukkit.getScheduler().runTaskAsynchronously( HarmonyChat.getInstance(), () -> {
            HarmonyChat.getController().reloadPlugin();
            sender.sendMessage(messages.chatReloaded());
        });
        return false;
    }
}
