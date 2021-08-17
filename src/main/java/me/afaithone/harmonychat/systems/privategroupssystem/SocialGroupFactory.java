package me.afaithone.harmonychat.systems.privategroupssystem;

import java.util.UUID;

public class SocialGroupFactory {

    public static SocialGroup create(UUID adminId) {
        SocialGroup socialGroup = new SocialGroup(adminId);

        socialGroup.addMember(adminId);

        return socialGroup;
    }
}
