package br.com.thelegion.legioncommons.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

public class CommandUtils {

	public static CommandMap getCommandMap() {
		return ((CraftServer) Bukkit.getServer()).getCommandMap();
	}
}
