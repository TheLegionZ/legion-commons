package br.com.thelegion.legioncommons.event.wrappers;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EventWrapper extends Event {

	private static final HandlerList HANDLER_LIST = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	public EventWrapper(boolean isAsync) {
		super(isAsync);
	}

	public EventWrapper() {
		super(false);
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLER_LIST;
	}
}