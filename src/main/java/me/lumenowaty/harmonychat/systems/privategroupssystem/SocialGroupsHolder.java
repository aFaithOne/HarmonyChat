package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.components.HMap;

import java.io.Serializable;
import java.util.UUID;

public class SocialGroupsHolder implements Serializable {

    private HMap<UUID, SocialGroup> socialGroups;
}
