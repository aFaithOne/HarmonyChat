package me.afaithone.harmonychat.systems.privategroupssystem;

import me.afaithone.harmonychat.components.HList;

import java.io.Serializable;
import java.util.UUID;

public class SocialGroup implements Serializable {

    private final HList<UUID> groupMembers;
    private final UUID groupAdmin;

    public SocialGroup(UUID groupAdmin) {
        this.groupMembers = new HList<>();
        this.groupAdmin = groupAdmin;
    }

    public void addMember(UUID uuid) {
        groupMembers.add(uuid);
    }

    public void removeMember(UUID uuid) {
        groupMembers.remove(uuid);
    }

    public boolean containsMember(UUID uuid) {
        return (groupMembers.contains(uuid) || groupAdmin.equals(uuid));
    }


    public HList<UUID> getGroupMembers() {
        return groupMembers;
    }

    public UUID getGroupAdmin() {
        return groupAdmin;
    }

    public boolean isPlayerAdmin(UUID uuid) {
        return groupAdmin.equals(uuid);
    }
}
