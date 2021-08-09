package br.com.thelegion.legioncommons.event;

import lombok.NonNull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class EventConsumers {

	private static final Consumer<? extends Cancellable> SET_CANCELLED = e -> e.setCancelled(true);
	private static final Consumer<? extends Cancellable> UNSET_CANCELLED = e -> e.setCancelled(false);

	@NonNull
	public static <T extends EntityEvent> Consumer<T> consumeEntity(Consumer<Entity> entity) {
		return event -> entity.accept(event.getEntity());
	}

	@NonNull
	public static <T extends PlayerEvent> Consumer<T> consumePlayer(Consumer<Player> consumer) {
		return event -> consumer.accept(event.getPlayer());
	}

	@Nonnull
	public static <T extends Cancellable> Consumer<T> cancel() {
		return (Consumer<T>) SET_CANCELLED;
	}

	@Nonnull
	public static <T extends Cancellable> Consumer<T> uncancel() {
		return (Consumer<T>) UNSET_CANCELLED;
	}
}
