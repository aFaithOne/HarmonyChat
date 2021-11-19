package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.HarmonyChat;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HMap;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Optional;

public class FormattedGroupFactory {

    private final Player player;
    private String format;

    public FormattedGroupFactory(Player player) {
        this.player = player;
    }

    public void prepareFormat() {
        ChatManager chatManager = HarmonyChat.getController().getChatManager();
        World world = player.getWorld();
        Chat chat = chatManager.getChat();
        try {
            String[] playerGroups = chat.getPlayerGroups(player);
            String groupPrefix = chat.getGroupPrefix(world, playerGroups[0]);
            HMap<String, FormattedGroup> formattedGroupMap = chatManager.getFormattedGroupsHolder().getFormattedGroupMap();
            Optional<FormattedGroup> byKey = formattedGroupMap.getByKey(groupPrefix);

            byKey.ifPresent(formattedGroup -> format = formattedGroup.getFormat());
        } catch (NullPointerException ex) {
            Bukkit.getLogger().info(PluginController.HARMONY_CHAT_PREFIX + " cannot find ranks plugin.");
        }

    }

    public String getFormat() {
        if (format == null) return FormattedGroup.DEFAULT_FORMAT();

        return format;
    }
}
