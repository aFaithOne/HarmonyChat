package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HController;

public class SocialGroupManager extends HController {

    private final SocialGroupsHolder holder;

    public SocialGroupManager(PluginController controller) {
        super(controller);

        holder = new SocialGroupsHolder();
    }

    public SocialGroupsHolder getHolder() {
        return holder;
    }
}
