package me.lumenowaty.harmonychat.systems.privatemessagessystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import me.lumenowaty.harmonychat.utils.HarmonyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;;

public class PrivateMessageCommand extends HCommandExecutor {

    public PrivateMessageCommand(PluginController controller) {
        super(controller);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesController messages = this.controller.getMessagesController();

        if (! (sender instanceof Player)) {
            sender.sendMessage(messages.inGameCommand());
            return false;
        }

        Player actor = (Player) sender;

        if (! actor.hasPermission("harmony.chat.privatemessage")) {
            actor.sendMessage(messages.noPermission());
            return false;
        }

        if (args.length < 2) {
            actor.sendMessage(messages.privateMessageUsage());
            return false;
        }

        Optional<Player> playerByName = HarmonyUtils.getPlayerByName(args[0]);

        if (! playerByName.isPresent()) {
            actor.sendMessage(messages.offlinePlayer());
            return false;
        }

        Player target = playerByName.get();

        Optional<Set<UUID>> targetKey = controller.getPrivateMessageManager().getIgnoreHolder().getIgnoreMap().getByKey(target.getUniqueId());

        if (targetKey.isPresent()) {
            if (targetKey.get().contains(actor.getUniqueId())) {
                actor.sendMessage(messages.privateMessageIgnored(target.getName()));
                return false;
            }
        }

        String message = ChatUtils.buildMessage(args, 1, args.length);

        PrivateMessageUtil.sendPrivateMessage(actor, target, message, controller, messages);
        return true;
    }
}
