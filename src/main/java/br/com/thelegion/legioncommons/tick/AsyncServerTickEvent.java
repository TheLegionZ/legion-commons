package br.com.thelegion.legioncommons.tick;

import br.com.thelegion.legioncommons.event.EventWrapper;
import org.bukkit.Bukkit;

public class AsyncServerTickEvent extends EventWrapper {

	final static AsyncServerTickEvent CACHED_EVENT = new AsyncServerTickEvent();

	public static void callEvent(){
		CACHED_EVENT.call();
	}

	void call(){
		Bukkit.getPluginManager().callEvent(this);
	}
}
