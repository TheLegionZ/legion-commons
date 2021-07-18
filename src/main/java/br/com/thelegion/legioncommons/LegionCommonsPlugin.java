package br.com.thelegion.legioncommons;

import br.com.thelegion.legioncommons.menu.MenuProvider;
import br.com.thelegion.legioncommons.tick.ServerTickEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public final class LegionCommonsPlugin extends JavaPlugin {

	private static final AtomicInteger TICK_COUNTER = new AtomicInteger(0);

	private MenuProvider menuProvider;

	@Override
	public void onEnable() {
		menuProvider = new MenuProvider(this);

		Bukkit.getScheduler().runTaskTimer(this, () -> ServerTickEvent.callEvent(TICK_COUNTER.getAndIncrement()), 0, 1);
	}

	@Override
	public void onDisable() {

	}

	public static LegionCommonsPlugin getInstance() {
		return getPlugin(LegionCommonsPlugin.class);
	}
}
