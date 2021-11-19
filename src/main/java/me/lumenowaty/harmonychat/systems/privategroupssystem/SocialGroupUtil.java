package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class SocialGroupUtil {

    public static void sendMessageToGroup(Player actor, String message) {
        PluginController controller = HarmonyChat.getController();
        MessagesController messages = controller.getMessagesController();
        SocialGroupManager socialGroupManager = controller.getSocialGroupManager();
        Optional<SocialGroup> socialGroupOfPlayer = socialGroupManager.getSocialGroupOfPlayer(actor);

        if (! socialGroupOfPlayer.isPresent()) {
            actor.sendMessage(messages.privateGroupsNoGroup());
            return;
        }

        List<UUID> list = socialGroupOfPlayer.get().getGroupMembers().getList();

        List<UUID> collect = list.stream().filter(s -> {
            Player player = Bukkit.getPlayer(s);

            if (player != null) return player.isOnline();
            return false;
        }).collect(Collectors.toList());

        if (collect.size() == 1) {
            actor.sendMessage(messages.privateGroupsNoReceivers());
            return;
        }

        collect.forEach(s -> {
            Player player = Bukkit.getPlayer(s);
            if (player != null && ! actor.equals(player)) {
                player.sendMessage(messages.privateGroupsPrefixReceive(actor, message));
            }
        });
        actor.sendMessage(messages.privateGroupsPrefixSend(message));
    }
}
