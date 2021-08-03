package br.com.thelegion.legioncommons.chat.replacer;

import org.bukkit.ChatColor;

import java.util.function.Function;

public class StringReplacer {

	private Function<String, String> baseFunction;

	private StringReplacer(Function<String, String> baseFunction) {
		this.baseFunction = baseFunction;
	}

	private StringReplacer() {
		this(s -> ChatColor.translateAlternateColorCodes('&', s));
	}

	public static StringReplacer replacing(String placeholder, Object object) {
		return new StringReplacer().and(placeholder, object);
	}

	public static StringReplacer replacing(char oldChar, char replacement) {
		return new StringReplacer().and(oldChar, replacement);
	}


	public static StringReplacer replacing(String placeholder, String replacement) {
		return new StringReplacer().and(placeholder, replacement);
	}

	public StringReplacer and(String placeholder, Object object) {
		baseFunction = baseFunction.andThen(string -> string.replace(placeholder, String.valueOf(object)));
		return this;
	}

	public StringReplacer and(String placeholder, String replacement) {
		baseFunction = baseFunction.andThen(string -> string.replace(placeholder, replacement));
		return this;
	}

	public StringReplacer and(char oldChar, char replacement) {
		baseFunction = baseFunction.andThen(string -> string.replace(oldChar, replacement));
		return this;
	}

	public String transform(String input) {
		return toFunction().apply(input);
	}

	public Function<String, String> toFunction() {
		return baseFunction;
	}
}
