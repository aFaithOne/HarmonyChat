package me.lumenowaty.harmonychat.systems.privatemessagessystem;

import me.lumenowaty.harmonychat.MessagesController;
import me.lumenowaty.harmonychat.PluginController;
import me.lumenowaty.harmonychat.components.HCommandExecutor;
import me.lumenowaty.harmonychat.components.HMap;
import me.lumenowaty.harmonychat.utils.HarmonyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class IgnorePlayerCommand extends HCommandExecutor {

    public IgnorePlayerCommand(PluginController controller) {
        super(controller);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MessagesController messages = controller.getMessagesController();

        if (! (sender instanceof Player)) {
            sender.sendMessage(messages.inGameCommand());
            return false;
        }

        Player actor = (Player) sender;

        if (! actor.hasPermission("harmony.chat.ignore")) {
            actor.sendMessage(messages.noPermission());
            return false;
        }

        UUID actorId = actor.getUniqueId();
        IgnorePlayerHolder holder = controller.getPrivateMessageManager().getIgnoreHolder();
        HMap<UUID, Set<UUID>> ignoreMap = holder.getIgnoreMap();
        Optional<Set<UUID>> byKey = ignoreMap.getByKey(actorId);

        if (args.length < 1) {
            String ignoreList = "";
            if (byKey.isPresent()) {
                ignoreList = byKey.get().stream().map(s -> {
                    OfflinePlayer player = Bukkit.getPlayer(UUID.fromString(s.toString()));
                    if (player == null) player = Bukkit.getOfflinePlayer(UUID.fromString(s.toString()));
                    return Objects.requireNonNull(player).getName();
                }).collect(Collectors.joining(", "));
            }
            actor.sendMessage(messages.ignoreList(ignoreList));
            return true;
        }

        Optional<Player> targetByName = HarmonyUtils.getPlayerByName(args[0]);

        if (! targetByName.isPresent()) {
            actor.sendMessage(messages.offlinePlayer());
            return false;
        }

        Player target = targetByName.get();
        UUID targetId = target.getUniqueId();

        if (target.hasPermission("harmony.chat.antiIgnore")) {
            actor.sendMessage(messages.ignoreNotAllowed());
            return false;
        }

        if (! byKey.isPresent()) {
            Set<UUID> set = new HashSet<>();
            set.add(targetId);
            ignoreMap.add(actorId, set);
            actor.sendMessage(messages.ignoreAdded(target.getName()));
            return true;
        }

        Set<UUID> uuids = byKey.get();

        if (uuids.contains(targetId)) {
            uuids.remove(targetId);
            actor.sendMessage(messages.ignoreRemoved(target.getName()));
            return true;
        }

        uuids.add(targetId);
        actor.sendMessage(messages.ignoreAdded(target.getName()));
        return true;
    }
}
