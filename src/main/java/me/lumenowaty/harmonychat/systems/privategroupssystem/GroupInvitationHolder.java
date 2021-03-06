package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.components.HMap;

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
