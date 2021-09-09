package br.com.thelegion.legioncommons.menu;

import br.com.thelegion.legioncommons.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class MenuItem {

	private final static Consumer<InventoryClickEvent> EMPTY_EVENT_CONSUMER = e -> e.setCancelled(true);

	private final static MenuItem EMPTY_ITEM = new MenuItem(new ItemStack(Material.AIR));

	private final ItemStack itemStack;
	private final Consumer<InventoryClickEvent> clickAction;

	public MenuItem(ItemStack itemStack, Consumer<InventoryClickEvent> clickAction) {
		this.itemStack = itemStack;
		this.clickAction = clickAction;
	}


	public MenuItem(ItemStack itemStack) {
		this(itemStack, EMPTY_EVENT_CONSUMER);
	}

	public MenuItem(ItemBuilder builder) {
		this(builder.build());
	}

	public static MenuItem emptyItem() {
		return EMPTY_ITEM;
	}
}