package br.com.thelegion.legioncommons.service.item;

import br.com.thelegion.legioncommons.annotations.BukkitService;
import br.com.thelegion.legioncommons.chat.util.TextUtil;
import br.com.thelegion.legioncommons.item.ItemBuilder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@BukkitService
public final class ConfigurableItemService {

	final static TextUtil TEXT_UTIL = TextUtil.getInstance();

	private final ConfigurationSection section;

	public ConfigurableItemService(ConfigurationSection section) {
		this.section = section;
	}

	public Optional<ItemStack> findItem(String path) {
		return Optional.empty();
	}

	public ItemStack getItem(String path) {
		return findItem(path).orElse(null);
	}

	private ItemStack fromSection(ConfigurationSection section) {
		if (section == null) {
			return null;
		}

		// id:data
		MaterialData id = parseMaterialData(section.getString("id"));
		String itemName = TEXT_UTIL.stylish(section.getString("name"));
		List<String> lore = section.getStringList("lore")
			.stream()
			.map(TEXT_UTIL::stylish)
			.collect(Collectors.toList());

		return new ItemBuilder(id)
			.name(itemName)
			.lore(lore)
			.build();
	}

	private MaterialData parseMaterialData(String input) {
		try {
			int id;
			int data = 0;

			if (input.contains(":")) {
				String[] split = input.split(":");
				id = Integer.parseInt(split[0]);
				data = Integer.parseInt(split[1]);
			} else {
				id = Integer.parseInt(input);
			}

			return new MaterialData(id, (byte) data);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao obter o id do input" + input);
		}
	}
}
