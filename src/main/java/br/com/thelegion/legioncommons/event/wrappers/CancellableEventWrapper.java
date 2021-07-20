package br.com.thelegion.legioncommons.event.wrappers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;

public class CancellableEventWrapper extends EventWrapper implements Cancellable {

	@Setter
	@Getter
	private boolean cancelled;

	public CancellableEventWrapper(boolean isAsync) {
		super(isAsync);
	}
}