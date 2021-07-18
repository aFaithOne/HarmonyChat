package me.lumenowaty.harmonychat.systems.pokesystem.Command;

import me.lumenowaty.harmonychat.HarmonyChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Poke implements CommandExecutor {
    public HashMap<String, Long> coolDowns = new HashMap<>();

    private HarmonyChat plugin;

    public Poke(HarmonyChat pl) {
        this.plugin = pl;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("Prefix"));
        int coolDown = this.plugin.getConfig().getInt("CoolDown", 30);
        if (this.coolDowns.containsKey(sender.getName())) {
            long target = ((Long)this.coolDowns.get(sender.getName())).longValue() / 1000L + coolDown - System.currentTimeMillis() / 1000L;
            if (target > 0L) {
                sender.sendMessage(prefix + prefix + "Musisz poczekac " + ChatColor.RED + " sekundy, zanim bedziesz mogl ponownie uzyc!");
                return true;
            }
        }
        if (label.equalsIgnoreCase("poke") && sender.hasPermission("poke.poke")) {
            if (args.length != 1) {
                sender.sendMessage(prefix + prefix + "Uzyj: /poke <player>");
                sender.sendMessage(prefix + prefix + "Uzyj: /pokes <player>");
                sender.sendMessage(prefix + prefix + "Uzyj: /pokesreload");
                return true;
            }
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + prefix + "Możesz uruchomic ta komende tylko jako gracz!");

        }
        return false;
    }
}


