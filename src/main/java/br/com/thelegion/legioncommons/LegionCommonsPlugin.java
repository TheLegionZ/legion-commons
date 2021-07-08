package br.com.thelegion.legioncommons;

import org.bukkit.plugin.java.JavaPlugin;

public final class LegionCommonsPlugin extends JavaPlugin {

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}

	public static LegionCommonsPlugin getInstance() {
		return getPlugin(LegionCommonsPlugin.class);
	}
}
