package br.com.thelegion.legioncommons.tick;

import br.com.thelegion.legioncommons.event.EventWrapper;
import org.bukkit.Bukkit;

public class ServerTickEvent extends EventWrapper {

	private static final ServerTickEvent EVENT = new ServerTickEvent(0);

	public static void callEvent(int currentTick) {
		Bukkit.getPluginManager().callEvent(EVENT.setCurrentCurrentTick(currentTick));
	}

	private int currentCurrentTick;

	public ServerTickEvent(int currentCurrentTick) {
		this.currentCurrentTick = currentCurrentTick;
	}

	public int getCurrentCurrentTick() {
		return currentCurrentTick;
	}

	ServerTickEvent setCurrentCurrentTick(int currentCurrentTick) {
		this.currentCurrentTick = 0;
		return this;
	}
}
