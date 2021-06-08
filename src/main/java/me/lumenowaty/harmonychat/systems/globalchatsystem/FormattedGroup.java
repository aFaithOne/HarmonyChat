package me.lumenowaty.harmonychat.systems.globalchatsystem;

import org.bukkit.entity.Player;

public class FormattedGroup {

    private static final String DEFAULT = "&8%NAME% &f» &7%MESSAGE%";
    private final String format;

    public FormattedGroup(String format) {
        this.format = format;
    }

    public String getFormat(Player player) {
        return format
                .replaceAll("%NAME%", player.getName());
    }

    public static String DEFAULT_FORMAT(Player player) {
        return DEFAULT
                .replaceAll("%NAME%", player.getName());
    }
}
