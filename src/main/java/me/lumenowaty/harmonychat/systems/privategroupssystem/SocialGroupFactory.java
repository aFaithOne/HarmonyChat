package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.HarmonyChat;

import java.util.Map;
import java.util.UUID;

public class SocialGroupFactory {

    public static SocialGroup create(UUID adminId) {
        SocialGroupsHolder holder = HarmonyChat.getController().getSocialGroupManager().getSocialGroupsHolder();
        Map<UUID, SocialGroup> map = holder.getSocialGroups().getMap();

        return new SocialGroup(map.size(), adminId);
    }
}
