package br.com.thelegion.legioncommons.item;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

public class Items {

	public static boolean hasSpace(Inventory inventory) {
		if (inventory.firstEmpty() != -1) {
			return true;
		}

		ItemStack[] contents = inventory.getContents();
		for (int i = 0, contentsLength = contents.length; i < contentsLength; i++) {
			ItemStack content = contents[i];
			if(content == null){
				return true;
			}
		}

		return false;
	}

	public static ItemStack findFirstItem(Inventory inventory, Predicate<ItemStack> predicate) {
		ItemStack[] contents = inventory.getContents();
		for (int i = 0, contentsLength = contents.length; i < contentsLength; i++) {
			ItemStack content = contents[i];
			if (predicate.test(content)) {
				return content;
			}
		}

		return null;
	}

}
