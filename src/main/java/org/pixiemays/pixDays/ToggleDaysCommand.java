package org.pixiemays.pixDays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ToggleDaysCommand implements CommandExecutor {

    private final PixDays plugin;

    public ToggleDaysCommand(PixDays plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            UUID playerUUID = player.getUniqueId();

            if (plugin.getDisabledPlayers().contains(playerUUID)) {
                plugin.getDisabledPlayers().remove(playerUUID);
                plugin.saveDisabledPlayers();
                player.sendMessage(ChatColor.GREEN + "Счетчик дней включен!");
            } else {
                plugin.getDisabledPlayers().add(playerUUID);
                plugin.saveDisabledPlayers();
                player.sendActionBar("");
                player.sendMessage(ChatColor.RED + "Счетчик дней выключен!");
            }
        } else {
            sender.sendMessage("Эта команда только для игроков!");
        }
        return true;
    }
}
