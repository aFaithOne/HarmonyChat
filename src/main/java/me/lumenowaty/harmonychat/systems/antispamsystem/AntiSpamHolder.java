package me.lumenowaty.harmonychat.systems.antispamsystem;

import me.lumenowaty.harmonychat.components.HMap;

import java.util.UUID;

public class AntiSpamHolder {

    private final HMap<UUID, ASPMessage> timeIntervalMessageMap;

    public AntiSpamHolder() {
        this.timeIntervalMessageMap = new HMap<>();
    }

    public HMap<UUID, ASPMessage> getTimeIntervalMessageMap() {
        return timeIntervalMessageMap;
    }
}
