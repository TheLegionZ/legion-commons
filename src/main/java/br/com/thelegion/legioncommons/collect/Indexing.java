package br.com.thelegion.legioncommons.collect;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * An indexing utility.
 */
public final class Indexing {

	private Indexing() {

	}

	public static <I, R> Map<I, R> index(Iterable<? extends R> values, Function<? super R, ? extends I> indexFunction) {
		requireNonNull(indexFunction, "indexFunction");
		return indexMultiple(values, r -> Collections.singleton(indexFunction.apply(r)));
	}

	public static <I, R> Map<I, R> indexMultiple(Iterable<? extends R> values, Function<? super R, ? extends Iterable<? extends I>> indexFunction) {
		requireNonNull(values, "values");
		requireNonNull(indexFunction, "indexFunction");

		Map<I, R> map = new HashMap<>();
		for (R value : values) {
			Iterable<? extends I> indexes = indexFunction.apply(value);
			for (I index : indexes) {
				R prev = map.put(index, value);
				if (prev != null) {
					throw new IllegalStateException("An index for " + value + " (" + index + ") was already associated with " + prev);
				}
			}
		}
		return ImmutableMap.copyOf(map);
	}

	public static <I, R> Map<I, R> index(R[] values, Function<? super R, ? extends I> indexFunction) {
		requireNonNull(values, "values");
		return index(Arrays.asList(values), indexFunction);
	}

	public static <I, R> Map<I, R> indexMultiple(R[] values, Function<? super R, ? extends Iterable<? extends I>> indexFunction) {
		requireNonNull(values, "values");
		return indexMultiple(Arrays.asList(values), indexFunction);
	}

	public static <R extends Enum<?>> Map<String, R> indexFromEnumName(Class<? extends R> enumClass) {
		requireNonNull(enumClass, "enumClass");

		R[] enumConstants = enumClass.getEnumConstants();
		if (enumConstants == null) {
			throw new IllegalArgumentException("Type is not an enum: " + enumClass);
		}
		return index(enumConstants, Enum::name);
	}
}