package br.com.thelegion.legioncommons.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class MenuProvider implements Listener {

	private final Plugin plugin;

	public MenuProvider(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onInventoryClick(InventoryClickEvent event) {
		Menu menu = Menu.getMenuByInventory(event.getInventory());
		if (menu == null || !menu.getPlayer().equals(event.getWhoClicked())) {
			return;
		}

		event.setCancelled(true);

		MenuItem item = menu.getItem(event.getRawSlot());
		if (item != null) {
			item.getClickAction().accept(event);
		}
	}

}