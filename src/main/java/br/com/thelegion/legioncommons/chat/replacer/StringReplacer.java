package br.com.thelegion.legioncommons.chat.replacer;

import org.bukkit.ChatColor;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <code>
 * Player player = Players.getPlayer
 * ("Sasuked");
 * String firstJoinMessage = "Olá @player, bem vindo ao servidor @serverName (id @serverId)";
 * <p>
 * String replacedMessage = StringReplacer.replacing("@player", player::getName)
 * 	.and("@serverName", "RedeNetwork")
 * 	.and("@serverId", "01")
 * 	.transform(firstJoinMessage);
 *
 * </code>
 */
public class StringReplacer {

	private Function<String, String> baseFunction;

	private StringReplacer(Function<String, String> baseFunction) {
		this.baseFunction = baseFunction;
	}

	private StringReplacer() {
		this(s -> ChatColor.translateAlternateColorCodes('&', s));
	}

	public static StringReplacer replacing(char oldChar, char replacement) {
		return new StringReplacer().and(oldChar, replacement);
	}

	public static StringReplacer replacing(String placeholder, String replacement) {
		return new StringReplacer().and(placeholder, replacement);
	}

	public static StringReplacer replacing(String placeholder, Object object) {
		return replacing(placeholder, String.valueOf(object));
	}

	public static StringReplacer replacing(String placeholder, Supplier<String> stringSupplier) {
		return replacing(placeholder, stringSupplier.get());
	}

	public StringReplacer and(String placeholder, String replacement) {
		return andThen(string -> string.replace(placeholder, replacement));
	}

	public StringReplacer and(String placeholder, Object object) {
		return and(placeholder, String.valueOf(object));
	}

	public StringReplacer and(char oldChar, char replacement) {
		return andThen(string -> string.replace(oldChar, replacement));
	}

	public StringReplacer and(String replacer, Supplier<String> replacementSupplier) {
		return and(replacer, replacementSupplier.get());
	}

	public String transform(String input) {
		return toFunction().apply(input);
	}

	public String apply(String s) {
		return baseFunction.apply(s);
	}

	public StringReplacer andThen(Function<String, String> after) {
		return new StringReplacer(baseFunction.andThen(after));
	}

	public Function<String, String> toFunction() {
		return baseFunction;
	}
}
