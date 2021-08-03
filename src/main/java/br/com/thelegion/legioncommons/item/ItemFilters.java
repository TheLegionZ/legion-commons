package br.com.thelegion.legioncommons.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.function.Predicate;

public class ItemFilters {

	public static Predicate<ItemStack> predicateItemMeta(Predicate<ItemMeta> predicate) {
		return itemStack -> {
			ItemMeta itemMeta = itemStack.getItemMeta();
			if (itemMeta == null) {
				return false;
			}

			return predicate.test(itemMeta);
		};
	}


	public static Predicate<ItemStack> itemNameEqualsTo(String displayName) {
		return itemStack -> {
			ItemMeta itemMeta = itemStack.getItemMeta();
			if (itemMeta == null) {
				return false;
			}

			return itemMeta.hasDisplayName() && itemMeta.getDisplayName().equalsIgnoreCase(displayName);
		};
	}

}
