package br.com.thelegion.legioncommons;

import br.com.thelegion.legioncommons.menu.MenuProvider;
import br.com.thelegion.legioncommons.plugin.PluginUtils;
import br.com.thelegion.legioncommons.tick.AsyncServerTickEvent;
import br.com.thelegion.legioncommons.tick.ServerTickEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class LegionCommonsPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginUtils.registerPluginListeners(this, new MenuProvider(this));

		Bukkit.getScheduler().runTaskTimerAsynchronously(this, AsyncServerTickEvent::callEvent, 0, 1);
		Bukkit.getScheduler().runTaskTimer(this, ServerTickEvent::callEvent, 0, 1);
	}

	@Override
	public void onDisable() {

	}

	public static LegionCommonsPlugin getInstance() {
		return getPlugin(LegionCommonsPlugin.class);
	}
}
