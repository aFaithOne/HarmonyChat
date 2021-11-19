package me.lumenowaty.harmonychat.utils;

import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ChatUtils {

    public static List<String> formatText(List<String> toFormat) {
        return toFormat.stream().map(ChatUtils::formatText).collect(Collectors.toList());
    }

    public static String formatText(String toFormat) {
        return ChatColor.translateAlternateColorCodes('&', toFormat);
    }

    public static TextComponent createPerformingCommandText(String text, String hoverText, String command) {
        TextComponent component = new TextComponent(text);
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverText).create()));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));

        return component;
    }

    public static String buildMessage(String[] args, int from, int to) {
        StringBuilder builder = new StringBuilder();

        for (int i = from; i < to; i++) {
            builder.append(args[i]).append(" ");
        }

        return builder.toString();
    }

    public static void playMessageSound(Player target, Sound sound) {
        target.playSound(target.getLocation(), sound, 1F, 1F);
    }

    public static void playerMessageSoundForAll(Sound sound) {
        Bukkit.getOnlinePlayers().forEach(player -> playMessageSound(player, sound));
    }
}
