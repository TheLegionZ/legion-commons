package br.com.thelegion.legioncommons.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MenuProvider implements Listener {

	private final Map<UUID, InventoryMenu> playerToMenuMap = new ConcurrentHashMap<>();

	public MenuProvider(Plugin plugin) {
		if (!plugin.isEnabled()) {
			throw new IllegalStateException("Plugin não foi inicializado corretamente.");
		}

		Bukkit.getPluginManager().registerEvents(this, plugin);
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new InternalMenuTickTask(plugin), 0, 0);
	}

	public void openMenu(Player player, InventoryMenu menu) {
		menu.open(player);
		playerToMenuMap.put(player.getUniqueId(), menu);
	}

	public InventoryMenu getOpenMenu(Player player) {
		return playerToMenuMap.get(player.getUniqueId());
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		InventoryMenu openMenu = this.getOpenMenu(player);
		if (openMenu == null) {
			return;
		}

		openMenu.handleClickEvent(event);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onClose(InventoryCloseEvent event) {
		Player player = (Player) event.getPlayer();
		InventoryMenu openMenu = this.getOpenMenu(player);
		if (openMenu == null) {
			return;
		}

		openMenu.onClose(player);
		playerToMenuMap.remove(player.getUniqueId());
	}

	protected class InternalMenuTickTask implements Runnable {
		private final Plugin plugin;

		public InternalMenuTickTask(Plugin plugin) {
			this.plugin = plugin;
		}

		@Override
		public void run() {
			for (Player player : Bukkit.getOnlinePlayers()) {
				InventoryMenu menu = playerToMenuMap.get(player.getUniqueId());
				if (menu != null) {
					menu.onTickUpdate(player);
				}
			}
		}
	}
}
