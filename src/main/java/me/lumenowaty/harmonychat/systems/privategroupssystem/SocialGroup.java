package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.components.HList;

import java.io.Serializable;
import java.util.UUID;

public class SocialGroup implements Serializable {

    private final int id;
    private final HList<UUID> groupMembers;
    private final UUID groupAdmin;

    public SocialGroup(int nameId, UUID groupAdmin) {
        this.id = nameId;
        this.groupMembers = new HList<>();
        this.groupAdmin = groupAdmin;
    }

    public void addMember(UUID uuid) {
        this.groupMembers.add(uuid);
    }

    public void removeMember(UUID uuid) {
        this.groupMembers.remove(uuid);
    }

    public boolean containsMember(UUID uuid) {
        return this.groupMembers.contains(uuid);
    }

    public int getId() {
        return id;
    }

    public HList<UUID> getGroupMembers() {
        return groupMembers;
    }

    public UUID getGroupAdmin() {
        return groupAdmin;
    }
}
