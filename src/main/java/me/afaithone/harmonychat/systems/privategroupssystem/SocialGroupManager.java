package me.afaithone.harmonychat.systems.privategroupssystem;

import me.afaithone.harmonychat.PluginController;
import me.afaithone.harmonychat.components.HController;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SocialGroupManager extends HController {

    private final SocialGroupsHolder socialGroupsHolder;
    private final GroupInvitationHolder invitationHolder;
    private final SocialGroupPlayerStatusHolder socialGroupPlayerStatusHolder;

    public SocialGroupManager(PluginController controller) {
        super(controller);

        socialGroupPlayerStatusHolder = new SocialGroupPlayerStatusHolder();
        invitationHolder = new GroupInvitationHolder();
        socialGroupsHolder = new SocialGroupsHolder();
    }

    public void loadComponents() {
        SocialGroupLoader saver = new SocialGroupLoader(socialGroupsHolder);
        saver.load();

        this.socialGroupsHolder.getSocialGroups()
                .setMap(saver.getHolder().getSocialGroups().getMap());
    }

    public void saveComponents() {
        SocialGroupLoader saver = new SocialGroupLoader(socialGroupsHolder);
        saver.save();
    }

    public SocialGroupsHolder getSocialGroupsHolder() {
        return socialGroupsHolder;
    }

    public GroupInvitationHolder getInvitationHolder() {
        return invitationHolder;
    }

    public SocialGroupPlayerStatusHolder getSocialGroupPlayerStatusHolder() {
        return socialGroupPlayerStatusHolder;
    }

    public boolean isPlayerInGroup(Player player) {
        return getSocialGroupOfPlayer(player).isPresent();
    }

    public boolean isPlayerOwnerOfGroup(Player player) {
        return socialGroupsHolder.getSocialGroups().containsKey(player.getUniqueId());
    }

    public Optional<SocialGroup> getSocialGroupOfPlayer(Player player) {

        Map<UUID, SocialGroup> map = socialGroupsHolder.getSocialGroups().getMap();

        for (SocialGroup group : map.values()) {
            if (group.containsMember(player.getUniqueId())) return Optional.of(group);
        }

        return Optional.empty();
    }
}
