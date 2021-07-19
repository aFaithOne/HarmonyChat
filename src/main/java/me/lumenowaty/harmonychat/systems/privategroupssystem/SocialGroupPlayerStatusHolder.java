package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.components.HList;

import java.util.UUID;

public class SocialGroupPlayerStatusHolder {

    private final HList<UUID> playersWithActiveSocialGroupStatus;

    public SocialGroupPlayerStatusHolder() {
        playersWithActiveSocialGroupStatus = new HList<>();
    }

    public HList<UUID> getPlayersWithActiveSocialGroupStatus() {
        return playersWithActiveSocialGroupStatus;
    }
}
