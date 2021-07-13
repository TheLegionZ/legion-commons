package br.com.thelegion.legioncommons.service.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings("all")
public final class ConfigurableMessageService {

	final static Function<String, String> COLOR_FUNCTION = s -> ChatColor.translateAlternateColorCodes('&', s);

	private final ConfigurationSection section;

	public ConfigurableMessageService(ConfigurationSection section) {
		this.section = section;
	}

	public Optional<String> findMessage(String path) {
		return Optional.ofNullable(section.getString(path));
	}

	public Optional<List<String>> findMessageList(String path) {
		List<String> stringList = section.getStringList(path);
		if (stringList == null || stringList.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(stringList);
	}

	public boolean sendMessageIfPresent(CommandSender sender, String path, Function<String, String> replacer) {
		Optional<String> message = this.findMessage(path);
		if (message.isPresent()) {
			return sendSingleMessage(sender, message.get(), replacer);
		}

		Optional<List<String>> messageList = this.findMessageList(path);
		if (!messageList.isPresent()) {
			return false;
		}

		return sendListedMessage(sender , messageList.get() , replacer);
	}


	private boolean sendSingleMessage(CommandSender sender, String message, Function<String, String> replacer) {
		if (replacer != null) {
			message = joinFunction(replacer).apply(message);
		}

		sender.sendMessage(message);
		return true;
	}

	private boolean sendListedMessage(CommandSender sender, List<String> message, Function<String, String> replacer) {
		for (int i = 0; i < message.size(); i++) {
			sender.sendMessage(joinFunction(replacer).apply(message.get(i)));
		}

		return true;
	}

	private String colorize(String s) {
		return COLOR_FUNCTION.apply(s);
	}

	private Function<String, String> joinFunction(Function<String, String> replacer) {
		if (replacer == null) {
			return COLOR_FUNCTION;
		}

		return COLOR_FUNCTION.compose(replacer);
	}
}
