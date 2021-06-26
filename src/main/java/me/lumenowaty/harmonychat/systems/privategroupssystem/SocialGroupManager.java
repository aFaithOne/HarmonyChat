package me.lumenowaty.harmonychat.systems.privategroupssystem;

import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HController;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

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
        Map<UUID, SocialGroup> map = socialGroupsHolder.getSocialGroups().getMap();

        final SocialGroup[] group = new SocialGroup[1];
        Stream.of(map).forEach(s -> {
            SocialGroup socialGroup = map.get(s);
            if (socialGroup.containsMember(player.getUniqueId())) group[0] = socialGroup;
        });

        return Optional.ofNullable(group[0]);
    }
}
