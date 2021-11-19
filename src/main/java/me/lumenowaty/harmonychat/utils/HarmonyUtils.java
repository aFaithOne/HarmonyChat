package me.lumenowaty.harmonychat.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

public class HarmonyUtils {

    public static Optional<Player> getPlayerByName(String name) {
        return Optional.ofNullable(Bukkit.getPlayer(name));
    }
}
