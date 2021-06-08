package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.components.HMap;

public class FormattedGroupsHolder {

    private final HMap<String, FormattedGroup> formattedGroupMap = new HMap<>();

    public HMap<String, FormattedGroup> getFormattedGroupMap() {
        return formattedGroupMap;
    }
}
