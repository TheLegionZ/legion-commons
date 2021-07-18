package br.com.thelegion.legioncommons.cache;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LifecycledMap<K, V> {

	private final Map<K, V> backingMap;

	private InsertionListener<K, V> insertionListener;
	private RemovalListener<K, V> removalListener;

	LifecycledMap(Map<K, V> backingMap, InsertionListener<K, V> insertionListener, RemovalListener<K, V> removalListener) {
		this.backingMap = backingMap;
		this.insertionListener = insertionListener;
		this.removalListener = removalListener;
	}

	public LifecycledMap() {
		this.backingMap = new ConcurrentHashMap<>();
	}

	public static <K, V> LifecycledMapBuilder<K, V> builder() {
		return new LifecycledMapBuilder<K, V>();
	}

	public void put(K key, V value) {
		backingMap.put(key, value);
		if (insertionListener != null) {
			insertionListener.onInsert(key, value);
		}
	}

	public void remove(K key) {
		V remove = backingMap.remove(key);
		if (removalListener != null) {
			removalListener.onRemove(key, remove);
		}
	}

	public V get(K k) {
		return backingMap.get(k);
	}

	public V getOrDefault(K k, V def) {
		return backingMap.getOrDefault(k, def);
	}

	public Optional<V> find(K key) {
		return Optional.ofNullable(get(key));
	}

	public Map<K, V> asMap() {
		return backingMap;
	}

	interface InsertionListener<K, V> {
		void onInsert(K key, V value);
	}

	interface RemovalListener<K, V> {
		void onRemove(K key, @Nullable V value);
	}

	public static class LifecycledMapBuilder<K, V> {
		private Map<K, V> backingMap;
		private InsertionListener<K, V> insertionListener;
		private RemovalListener<K, V> removalListener;

		LifecycledMapBuilder() {
			backingMap = new ConcurrentHashMap<>();
		}

		public LifecycledMapBuilder<K, V> withDefaultEntry(K k, V v) {
			backingMap.put(k, v);
			return this;
		}

		public LifecycledMapBuilder<K, V> withDefaultEntries(Map<K, V> entries) {
			backingMap.putAll(entries);
			return this;
		}

		public LifecycledMapBuilder<K, V> withInsertionListener(InsertionListener<K, V> insertionListener) {
			this.insertionListener = insertionListener;
			return this;
		}

		public LifecycledMapBuilder<K, V> withRemovalListener(RemovalListener<K, V> removalListener) {
			this.removalListener = removalListener;
			return this;
		}

		public LifecycledMap<K, V> build() {
			return new LifecycledMap<K, V>(backingMap, insertionListener, removalListener);
		}

		public String toString() {
			return "LifecycledMap.LifecycledMapBuilder(backingMap=" + this.backingMap + ", insertionListener=" + this.insertionListener + ", removalListener=" + this.removalListener + ")";
		}
	}
}
