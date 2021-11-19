package me.lumenowaty.harmonychat.systems.privatemessagessystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HMap;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PrivateMessageUtil {

    public static void sendPrivateMessage(Player actor, Player target, String message, PluginController controller, MessagesController messages) {
        actor.sendMessage(messages.privateMessageSend(target.getName(), message));
        target.sendMessage(messages.privateMessageReceive(actor.getName(), message));
        ChatUtils.playMessageSound(target, Sound.BLOCK_NOTE_BLOCK_BELL);

        HMap<UUID, UUID> replyMap = controller.getPrivateMessageManager().getReplyHolder().getReplyMap();

        UUID targetId = target.getUniqueId();
        UUID actorId = actor.getUniqueId();

        replyMap.add(targetId, actorId);
        replyMap.add(actorId, targetId);
    }

}
