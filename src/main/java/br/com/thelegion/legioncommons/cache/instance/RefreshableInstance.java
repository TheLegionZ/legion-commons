package br.com.thelegion.legioncommons.cache.instance;

import lombok.Builder;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RefreshableInstance<T> {

	private final Supplier<T> supplier;

	private T currentValue;

	private final long timeToExpire;
	private final TimeUnit unit;

	long expirationTime;

	public RefreshableInstance(Supplier<T> supplier, long timeToExpire, TimeUnit unit) {
		this.supplier = supplier;
		this.timeToExpire = timeToExpire;
		this.unit = unit;
		this.expirationTime = System.currentTimeMillis() + (unit.toSeconds(timeToExpire) * 1000);
	}

	public T getCurrentValue() {
		return getCurrentValue(null);
	}

	public T getCurrentValue(T def) {
		if (currentValue == null || isExpired()) {
			currentValue = supplier.get();
			if (currentValue == null) {
				currentValue = def;
			}

			expirationTime = System.currentTimeMillis() + (unit.toSeconds(timeToExpire) * 1000);
		}

		return currentValue;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() >= expirationTime;
	}
}