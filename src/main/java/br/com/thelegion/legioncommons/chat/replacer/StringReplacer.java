package br.com.thelegion.legioncommons.chat.replacer;

import org.bukkit.ChatColor;

import java.util.function.Function;

public class StringReplacer {

	private Function<String, String> baseFunction;

	public StringReplacer(Function<String, String> baseFunction) {
		this.baseFunction = baseFunction;
	}

	public StringReplacer() {
		this(s -> ChatColor.translateAlternateColorCodes('&', s));
	}

	public StringReplacer replace(String placeholder, Object object) {
		baseFunction = baseFunction.andThen(string -> string.replace(placeholder, String.valueOf(object)));
		return this;
	}

	public StringReplacer replace(String placeholder, String replacement) {
		baseFunction = baseFunction.andThen(string -> string.replace(placeholder, replacement));
		return this;
	}

	public Function<String, String> toFunction() {
		return baseFunction;
	}
}
