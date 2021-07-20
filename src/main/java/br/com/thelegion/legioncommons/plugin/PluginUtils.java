package br.com.thelegion.legioncommons.plugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import javax.annotation.Nullable;

public class PluginUtils {

	public static boolean isPluginEnabled(String pluginName){
		Plugin pluginFromName = getPluginFromName(pluginName);
		return pluginFromName != null && pluginFromName.isEnabled();
	}

	public static Plugin getPluginFromName(String pluginName) {
		return Bukkit.getPluginManager().getPlugin(pluginName);
	}

	public static Plugin[] getPlugins() {
		return Bukkit.getPluginManager().getPlugins();
	}

	public static void registerPluginListeners(Plugin plugin, Listener... listeners) {
		PluginManager pluginManager = Bukkit.getPluginManager();
		for (Listener listener : listeners) {
			pluginManager.registerEvents(listener, plugin);
		}
	}

	public static void registerCommand(String prefix, Command... commands) {
		CommandMap commandMap = getCommandMap();
		for (Command command : commands) {
			commandMap.register(prefix, command);
		}
	}

	public static CommandMap getCommandMap() {
		return ((CraftServer) Bukkit.getServer()).getCommandMap();
	}
}
