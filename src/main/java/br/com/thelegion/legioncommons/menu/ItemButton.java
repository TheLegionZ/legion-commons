package br.com.thelegion.legioncommons.menu;

import lombok.Builder;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

@Data
@Builder
class ItemButton {

	public static final ItemButton EMPTY = ItemButton.builder().build();

	private ItemStack itemStack;
	private BiConsumer<Player, ItemStack> action;

}
