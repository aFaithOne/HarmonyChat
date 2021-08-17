package me.afaithone.harmonychat.systems.privategroupssystem;

import me.afaithone.harmonychat.components.HMap;

import java.util.UUID;

public class GroupInvitationHolder {

    private final HMap<UUID, UUID> invitationMap;

    public GroupInvitationHolder() {
        this.invitationMap = new HMap<>();
    }

    public HMap<UUID, UUID> getInvitationMap() {
        return invitationMap;
    }
}
