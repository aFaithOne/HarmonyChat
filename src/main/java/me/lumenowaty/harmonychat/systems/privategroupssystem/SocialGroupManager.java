package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HController;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SocialGroupManager extends HController {

    private final SocialGroupsHolder socialGroupsHolder;
    private final GroupInvitationHolder invitationHolder;

    public SocialGroupManager(PluginController controller) {
        super(controller);

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

    public boolean isPlayerInGroup(Player player) {
        return getSocialGroupOfPlayer(player).isPresent();
    }

    public boolean isPlayerOwnerOfGroup(Player player) {
        return socialGroupsHolder.getSocialGroups().containsKey(player.getUniqueId());
    }

    public Optional<SocialGroup> getSocialGroupOfPlayer(Player player) {
        SocialGroup[] group = new SocialGroup[1];
        Map<UUID, SocialGroup> map = socialGroupsHolder.getSocialGroups().getMap();
        Set<UUID> uuids = map.keySet();

        uuids.forEach(s -> {
            SocialGroup socialGroup = map.get(s);
            UUID playerId = player.getUniqueId();

            if (socialGroup.containsMember(playerId)) {
                group[0] = socialGroup;
            }
        });

        return Optional.ofNullable(group[0]);
    }
}
