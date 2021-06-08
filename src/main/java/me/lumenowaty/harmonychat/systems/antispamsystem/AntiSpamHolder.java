package me.lumenowaty.harmonychat.systems.antispamsystem;

import me.lumenowaty.harmonychat.components.HMap;

import java.time.LocalDateTime;
import java.util.UUID;

public class AntiSpamHolder {

    private final HMap<UUID, LocalDateTime> timeIntervalMessageMap;

    public AntiSpamHolder() {
        this.timeIntervalMessageMap = new HMap<>();
    }

    public HMap<UUID, LocalDateTime> getTimeIntervalMessageMap() {
        return timeIntervalMessageMap;
    }
}
