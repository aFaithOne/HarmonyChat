package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HListener;
import me.lumenowaty.harmonychat.components.HMap;
import me.lumenowaty.harmonychat.systems.antispamsystem.AntiSpamHolder;
import me.lumenowaty.harmonychat.systems.antispamsystem.AntiSpamManager;
import me.lumenowaty.harmonychat.utils.ChatUtils;
import me.lumenowaty.harmonychat.utils.HarmonyUtils;
import me.lumenowaty.harmonychat.utils.TimeUtils;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ChatListener extends HListener {

    public ChatListener(PluginController controller) {
        super(controller);
    }

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        MessagesController messages = this.controller.getMessagesController();
        ChatManager chatManager = this.controller.getChatManager();

        Player player = event.getPlayer();

        if (! this.canSendMessage(player, chatManager)) {

            event.setCancelled(true);
            player.sendMessage(messages
                    .chatOffDisable(chatManager.getChatStatus().getDescription()));
        }
        this.antiSpamCheck(player, event, messages);

        String message = event.getMessage();

        event.setFormat(this.getFormat(player, message));
        this.pokeMessage(message);
    }

    @EventHandler
    public void onBroadcastMessage(BroadcastMessageEvent event) {
        event.setMessage(ChatUtils.formatText(event.getMessage()));
    }

    private boolean canSendMessage(Player player, ChatManager chatManager) {
        if (chatManager.getChatStatus().getStatus()) return true;
        return (player.hasPermission("harmony.chat.bypass"));
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

    private void antiSpamCheck(Player target, AsyncPlayerChatEvent event, MessagesController messages) {
        if (target.hasPermission("harmony.chat.bypass")) return;

        AntiSpamManager antiSpamManager = controller.getAntiSpamManager();
        AntiSpamHolder holder = antiSpamManager.getAntiSpamHolder();
        HMap<UUID, LocalDateTime> timeIntervalMessageMap = holder.getTimeIntervalMessageMap();
        UUID targetUUID = target.getUniqueId();
        LocalDateTime now = LocalDateTime.now();
        Optional<LocalDateTime> byKey = timeIntervalMessageMap.getByKey(targetUUID);

        int timeout = antiSpamManager.getMessageTimeout();

        if (! byKey.isPresent()) {
            timeIntervalMessageMap.add(targetUUID, now.plusSeconds(timeout));
            return;
        }

        LocalDateTime localDateTime = byKey.get();

        if (now.isBefore(localDateTime)) {
            target.sendMessage(messages.antiSpamCoolDown(
                    String.valueOf(TimeUtils.getSecondsDifference(localDateTime, now)+1)));
            event.setCancelled(true);

            return;
        }

        timeIntervalMessageMap.add(targetUUID, now.plusSeconds(timeout));
    }

    private String getFormat(Player player, String message) {
        FormattedGroupFactory factory = new FormattedGroupFactory(player);

        factory.prepareFormat();
        String format = factory.getFormat();

        return ChatUtils.formatText(format.replaceAll("%MESSAGE%", message));
    }
}
