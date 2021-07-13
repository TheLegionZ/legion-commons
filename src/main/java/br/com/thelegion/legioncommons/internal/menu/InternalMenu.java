package br.com.thelegion.legioncommons.internal.menu;

import br.com.thelegion.legioncommons.item.ItemBuilder;
import br.com.thelegion.legioncommons.menu.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static br.com.thelegion.legioncommons.item.ItemBuilder.fromData;

public class InternalMenu extends InventoryMenu {

	private int clicks = 1;
	private int tickCount;
	private int currentBackGroundIndex;

	public InternalMenu() {
		super("Menu de teste", 3);

		int totalSlots = 9 * getRows();
		for (int i = 0; i < totalSlots; i++) {
			set(i, fromData(Material.STAINED_GLASS_PANE, (byte) currentBackGroundIndex).build());
		}

		createInventory();
	}

	@Override
	public void open(Player player) {
		super.open(player);
	}

	@Override
	public void onTickUpdate(Player player) {
		tickCount++;

		if (tickCount < 20) {
			return;
		}

		tickCount = 0;

		currentBackGroundIndex++;
		if (currentBackGroundIndex > 10) {
			currentBackGroundIndex = 0;
		}

		int totalSlots = 9 * getRows();
		for (int i = 0; i < totalSlots; i++) {
			set(i, fromData(Material.STAINED_GLASS_PANE, (byte) currentBackGroundIndex).build());
		}

		createInventory();
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