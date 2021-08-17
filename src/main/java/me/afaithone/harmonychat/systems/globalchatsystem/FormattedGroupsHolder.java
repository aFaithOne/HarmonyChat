package me.afaithone.harmonychat.systems.globalchatsystem;

import me.afaithone.harmonychat.components.HMap;

public class FormattedGroupsHolder {

    private final HMap<String, FormattedGroup> formattedGroupMap = new HMap<>();

    public HMap<String, FormattedGroup> getFormattedGroupMap() {
        return formattedGroupMap;
    }
}
