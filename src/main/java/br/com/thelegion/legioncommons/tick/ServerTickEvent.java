package br.com.thelegion.legioncommons.tick;

import br.com.thelegion.legioncommons.event.EventWrapper;
import org.bukkit.Bukkit;

public class ServerTickEvent extends EventWrapper {

	private static final ServerTickEvent EVENT = new ServerTickEvent();

	public static void callEvent() {
		Bukkit.getPluginManager().callEvent(EVENT);
	}
}
