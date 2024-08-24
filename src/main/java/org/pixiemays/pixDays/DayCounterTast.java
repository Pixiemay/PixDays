package org.pixiemays.pixDays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DayCounterTast extends BukkitRunnable {

    private final PixDays plugin;

    public DayCounterTast(PixDays plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        World world = Bukkit.getWorlds().get(0);
        if (world != null) {
            long dayCount = world.getFullTime() / 24000;

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!plugin.getDisabledPlayers().contains(player.getUniqueId())) {
                    player.sendActionBar(ChatColor.GREEN + "Прожито дней: " + ChatColor.WHITE + dayCount);
                }
            }
        }
    }
}
