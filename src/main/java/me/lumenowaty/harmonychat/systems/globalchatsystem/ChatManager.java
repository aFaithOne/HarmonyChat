package me.lumenowaty.harmonychat.systems.globalchatsystem;

import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HController;
import me.lumenowaty.harmonychat.components.HStatus;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class ChatManager extends HController {

    private final HStatus chatStatus;
    private final FormattedGroupsHolder formattedGroupsHolder;
    private final Chat chat;

    public ChatManager(PluginController controller) {
        super(controller);
        this.chatStatus = new HStatus();
        this.formattedGroupsHolder = new FormattedGroupsHolder();

        RegisteredServiceProvider<Chat> provider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);

        if (provider == null) {
            chat = null;
            return;
        }

        this.chat = provider.getProvider();
    }

    public void loadComponents() {
        FormattedGroupLoader formattedGroupLoader = new FormattedGroupLoader(this, controller);
        formattedGroupLoader.load();
    }

    public HStatus getChatStatus() {
        return chatStatus;
    }

    public FormattedGroupsHolder getFormattedGroupsHolder() {
        return formattedGroupsHolder;
    }

    public Chat getChat() {
        return chat;
    }
}
