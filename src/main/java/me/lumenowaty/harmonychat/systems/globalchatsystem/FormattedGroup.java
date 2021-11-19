package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.PluginController;

public class FormattedGroup {

    private static final String DEFAULT = "&8%NAME% &f» &7%MESSAGE%";
    private final String format;

    public FormattedGroup(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static String DEFAULT_FORMAT() {
        PluginController controller = HarmonyChat.getController();
        String string = controller.getMainConfig().getConfig().getString("globalChatFormat.groups.default");
        String format;
        if (string == null) {
            format = DEFAULT;
        } else {
            format = string;
        }

        return format;
    }
}
