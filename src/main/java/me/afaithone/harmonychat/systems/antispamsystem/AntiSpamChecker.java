package me.afaithone.harmonychat.systems.antispamsystem;

import me.afaithone.harmonychat.components.HMap;
import me.afaithone.harmonychat.HarmonyChat;
import me.afaithone.harmonychat.utils.TimeUtils;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class AntiSpamChecker {

    private final Player target;
    private final String message;

    private int time;

    public AntiSpamChecker(Player target, String message) {
        this.target = target;
        this.message = message;
        this.time = 0;
    }

    public boolean isMessageSpam() {
        if (target.hasPermission("harmony.chat.bypass")) return false;

        AntiSpamManager antiSpamManager = HarmonyChat.getController().getAntiSpamManager();
        AntiSpamHolder holder = antiSpamManager.getAntiSpamHolder();
        HMap<UUID, ASPMessage> antiSpamMap = holder.getTimeIntervalMessageMap();
        UUID targetUUID = target.getUniqueId();
        LocalDateTime now = LocalDateTime.now();
        Optional<ASPMessage> byKey = antiSpamMap.getByKey(targetUUID);
        int timeout = antiSpamManager.getMessageTimeout();
        ASPMessage aspMessage = new ASPMessage(message, now.plusSeconds(timeout));


        if (! byKey.isPresent()) {
            antiSpamMap.add(targetUUID, aspMessage);
            return false;
        }

        LocalDateTime localDateTime = byKey.get().getTime();

        if (now.isBefore(localDateTime)) {
            time = (int) TimeUtils.getSecondsDifference(localDateTime, now)+1;
            return true;
        }
        antiSpamMap.put(targetUUID, aspMessage);
        return false;
    }

    public String getTimeRemainingMessage() {
        return String.valueOf(time);
    }
}
