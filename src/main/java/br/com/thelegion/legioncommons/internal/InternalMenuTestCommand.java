package br.com.thelegion.legioncommons.internal;

import br.com.thelegion.legioncommons.LegionCommonsPlugin;
import br.com.thelegion.legioncommons.item.ItemBuilder;
import br.com.thelegion.legioncommons.menu.InventoryMenu;
import br.com.thelegion.legioncommons.menu.MenuProvider;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InternalMenuTestCommand extends Command {

	private final MenuProvider provider;

	public InternalMenuTestCommand(LegionCommonsPlugin plugin) {
		super("testmenu");

		this.setPermission("legioncommons.testmenu");

		this.provider = plugin.getMenuProvider();
	}

	@Override
	public boolean execute(CommandSender commandSender, String s, String[] strings) {
		if (!commandSender.hasPermission(getPermission())) {
			return false;
		}

		if (!(commandSender instanceof Player)) {
			return false;
		}


		provider.openMenu(((Player) commandSender), new InternalMenu());
		return false;
	}

	private class InternalMenu extends InventoryMenu {
		private int clicks = 1;

		public InternalMenu() {
			super("Menu de teste", 3);
		}

		@Override
		public void open(Player player) {
			createInventory();
			super.open(player);
		}

		public void createInventory() {
			set(11, new ItemBuilder(Material.ARROW)
				.name("§c- 1")
				.build(), (p, i) -> {
				clicks--;
				if (clicks <= 1) {
					clicks = 1;
				}

				createInventory();
			});

			set(13, new ItemBuilder(Material.APPLE)
				.amount(Math.min(clicks, 64))
				.build());

			set(15, new ItemBuilder(Material.ARROW)
				.name("§a+ 1")
				.build(), (p, i) -> {
				clicks++;
				if (clicks >= 64) {
					clicks = 64;
				}

				createInventory();
			});
		}
	}
}
