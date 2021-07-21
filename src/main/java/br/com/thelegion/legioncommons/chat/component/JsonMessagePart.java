package br.com.thelegion.legioncommons.chat.component;


import br.com.thelegion.legioncommons.chat.component.fanciful.FancyMessage;
import br.com.thelegion.legioncommons.chat.util.TextUtil;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class JsonMessagePart implements MessagePart {

	private final String text;
	private String command, suggest, link, file, achievement;
	private ItemStack itemStack;
	private List<String> tooltip;

	public JsonMessagePart(String text) {
		this.text = text;
	}

	public JsonMessagePart command(String command) {
		this.command = command;
		return this;
	}

	public JsonMessagePart suggest(String suggest) {
		this.suggest = suggest;
		return this;
	}

	public JsonMessagePart link(String link) {
		this.link = link;
		return this;
	}

	public JsonMessagePart file(String file) {
		this.file = file;
		return this;
	}

	public JsonMessagePart achievement(String achievement) {
		this.achievement = achievement;
		return this;
	}

	public JsonMessagePart item(ItemStack itemStack) {
		this.itemStack = itemStack;
		return this;
	}

	public JsonMessagePart tooltip(List<String> tooltip) {
		this.tooltip = tooltip;
		return this;
	}

	public JsonMessagePart tooltip(String... tooltip) {
		return tooltip(Arrays.asList(tooltip));
	}

	@Override
	public FancyMessage append(FancyMessage fancyMessage) {
		TextUtil textUtil = TextUtil.getInstance();

		LegacyConverter.getMessageParts(fancyMessage.getLastColors() + textUtil.stylish(text))
			.forEach(messagePart -> {
			fancyMessage.then(messagePart);

			if (command != null)
				fancyMessage.command(command);

			if (suggest != null)
				fancyMessage.suggest(suggest);

			if (link != null)
				fancyMessage.link(link);

			if (file != null)
				fancyMessage.file(file);

			if (achievement != null)
				fancyMessage.achievementTooltip(achievement);

			if (itemStack != null)
				fancyMessage.item(itemStack);

			if (tooltip != null && !tooltip.isEmpty())
				fancyMessage.tooltip(tooltip);

		});

		return fancyMessage;
	}

	@Override
	public String getText() {
		return text;
	}
}