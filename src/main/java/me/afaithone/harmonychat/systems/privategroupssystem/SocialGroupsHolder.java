package me.afaithone.harmonychat.systems.privategroupssystem;

import me.afaithone.harmonychat.components.HMap;

import java.io.Serializable;
import java.util.UUID;

public class SocialGroupsHolder implements Serializable {

    private final HMap<UUID, SocialGroup> socialGroups;

    public SocialGroupsHolder() {
        this.socialGroups = new HMap<>();
    }

    public HMap<UUID, SocialGroup> getSocialGroups() {
        return socialGroups;
    }
}
