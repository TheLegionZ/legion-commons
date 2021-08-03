package br.com.thelegion.legioncommons.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Players {

	public static List<? extends Player> getOnlinePlayers() {
		return ((CraftServer) Bukkit.getServer()).getOnlinePlayers();
	}

	public static List<Player> getWorldPlayers(World world) {
		return world.getPlayers();
	}

	public static Player getPlayer(String playerName) {
		for (Player it : Bukkit.getOnlinePlayers()) {
			if (it.getName().equalsIgnoreCase(playerName)) {
				return it;
			}
		}
		return null;
	}

	public static Player getPlayer(UUID uniqueId) {
		return Bukkit.getPlayer(uniqueId);
	}

	public static OfflinePlayer getOfflinePlayer(String playerName) {
		for (OfflinePlayer it : Bukkit.getOfflinePlayers()) {
			if (it.getName().equalsIgnoreCase(playerName)) {
				return it;
			}
		}
		return null;
	}
}
