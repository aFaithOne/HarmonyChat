package me.afaithone.harmonychat.systems.privatemessagessystem;

import me.afaithone.harmonychat.MessagesController;
import me.afaithone.harmonychat.PluginController;
import me.afaithone.harmonychat.components.HCommandExecutor;
import me.afaithone.harmonychat.components.HMap;
import me.afaithone.harmonychat.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ReplyMessageCommand extends HCommandExecutor {

    public ReplyMessageCommand(PluginController controller) {
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

        if (! actor.hasPermission("harmony.chat.reply")) {
            actor.sendMessage(messages.noPermission());
            return false;
        }

        if (args.length < 1) {
            actor.sendMessage(messages.replyUsage());
            return false;
        }

        String message = ChatUtils.buildMessage(args, 0, args.length);

        HMap<UUID, UUID> replyMap = controller
                .getPrivateMessageManager()
                .getReplyHolder()
                .getReplyMap();

        Optional<UUID> actorByKey = replyMap.getByKey(actor.getUniqueId());

        if (! actorByKey.isPresent()) {
            actor.sendMessage(messages.replyNoPlayerToReply());
            return false;
        }

        Player target = Bukkit.getPlayer(actorByKey.get());

        if (target == null) {
            actor.sendMessage(messages.replyOfflinePlayer());
            return false;
        }

        Optional<Set<UUID>> targetKey = controller.getPrivateMessageManager().getIgnoreHolder().getIgnoreMap().getByKey(target.getUniqueId());

        if (targetKey.isPresent()) {
            if (targetKey.get().contains(actor.getUniqueId())) {
                actor.sendMessage(messages.privateMessageIgnored(target.getName()));
                return false;
            }
        }

        PrivateMessageUtil.sendPrivateMessage(actor, target, message, controller, messages);
        return true;
    }
}
