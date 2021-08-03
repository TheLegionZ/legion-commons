package br.com.thelegion.legioncommons.function;

import java.util.function.Function;

@FunctionalInterface
public interface UncheckedFunction<I, O> extends Function<I, O> {

	static <I , O> UncheckedFunction<I , O> wrap(Function<I , O> function){
		return function::apply;
	}

	O uncheckedApply(I input) throws Exception;

	@Override
	default O apply(I i) {
		try {
			return uncheckedApply(i);
		} catch (Exception e) {
			return null;
		}
	}
}
