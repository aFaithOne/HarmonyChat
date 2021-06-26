package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HListener;
import me.lumenowaty.harmonychat.systems.antispamsystem.AntiSpamChecker;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import me.lumenowaty.harmonychat.utils.HarmonyUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChatListener extends HListener {

    public ChatListener(PluginController controller) {
        super(controller);
    }

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        MessagesController messages = this.controller.getMessagesController();

        Player actor = event.getPlayer();

        this.canSendMessage(actor, event, messages);
        this.checkForSpam(actor, event, messages);

        String message = event.getMessage();

        this.setFormat(actor.getPlayer(), message, event);
        this.pokeMessage(message);
    }

    @EventHandler
    public void onBroadcastMessage(BroadcastMessageEvent event) {
        event.setMessage(ChatUtils.formatText(event.getMessage()));
    }

    private void canSendMessage(Player actor, AsyncPlayerChatEvent event, MessagesController messages) {
        ChatManager chatManager = this.controller.getChatManager();
        if (chatManager.getChatStatus().getStatus()) return;

        event.setCancelled(true);
        actor.sendMessage(messages.chatOffDisable(chatManager.getChatStatus().getDescription()));
    }

    private void pokeMessage(String message) {
        if (! message.contains("@")) return;

        String[] names = Arrays.stream(message.split(" "))
                .filter(s -> s.contains("@"))
                .collect(Collectors.joining(" "))
                .replaceAll("@", "")
                .split(" ");

        for (String name : names) {
            Optional<Player> targetOptional = HarmonyUtils.getPlayerByName(name);

            if (! targetOptional.isPresent()) return;

            Player target = targetOptional.get();

            ChatUtils.playMessageSound(target, Sound.BLOCK_NOTE_BLOCK_HARP);
        }
    }

    private void checkForSpam(Player target,AsyncPlayerChatEvent event, MessagesController messages) {
        if (target.hasPermission("harmony.chat.bypass")) return;

        AntiSpamChecker checker = new AntiSpamChecker(target, event.getMessage());

        if (checker.isMessageSpam()) {
            target.sendMessage(messages.antiSpamCoolDown(checker.getTimeRemainingMessage()));
            event.setCancelled(true);
        }
    }

    private void setFormat(Player player, String message, AsyncPlayerChatEvent event) {
        FormattedGroupFactory factory = new FormattedGroupFactory(player);

        factory.prepareFormat();
        String format = factory.getFormat();
        String s = ChatUtils.formatText(format
                .replaceAll("%MESSAGE%", message)
                .replaceAll("%NEMAE%", player.getDisplayName()));

        event.setFormat(s);
    }
}
