package br.com.thelegion.legioncommons.function;

@FunctionalInterface
public interface TriConsumer<H, O, F> {

	void accept(H h, O o, F f);

	default TriConsumer<H, O, F> andThen(TriConsumer<? super H, ? super O, ? super F> consumer) {
		return (h, o, f) -> {
			accept(h, o, f);
			consumer.accept(h, o, f);
		};
	}
}
