package br.com.thelegion.legioncommons.menu;

import br.com.thelegion.legioncommons.item.ItemBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;
import java.util.function.Consumer;

import static java.util.Collections.synchronizedMap;

@Getter
public class Menu implements InventoryHolder {


	// item default de fill
	public static final ItemStack BLACK_GLASS_PANE = new ItemBuilder(new MaterialData(160, (byte) 7))
		.name("§c")
		.lore("")
		.amount(1)
		.build();

	@Getter(AccessLevel.NONE)
	protected final Player player;

	private String name;

	private int rows;

	private final Map<Integer, MenuItem> itemMap;

	private final Map<String, Object> context;

	@Setter
	private boolean darkThemeEnabled;

	public Menu(Player player, String name, int rows) {
		this.player = player;
		this.name = name;
		this.rows = rows;
		this.itemMap = synchronizedMap(new WeakHashMap<>());
		this.context = synchronizedMap(new WeakHashMap<>());
	}

	public void render() {
		if (player == null) {
			throw new NoSuchElementException("Jogador inválido para usar o menu!");
		}
	}

	public void open() {
		this.render();

		player.openInventory(getInventory());
	}

	public void refreshInventory() {
		if (player == null || player.getOpenInventory().getTopInventory() == null) {
			return;
		}

		if ((!(player.getOpenInventory().getTopInventory().getHolder() instanceof Menu))
			|| !player.getOpenInventory().getTopInventory().getHolder().equals(this)) {
			return;
		}

		if (player.getOpenInventory().getTopInventory().getSize() != getRows() * 9) {
			player.openInventory(getInventory());
			return;
		}

		if (!player.getOpenInventory().getTopInventory().getTitle().equals(name)) {
			player.openInventory(getInventory());
			return;
		}

		player.getOpenInventory().getTopInventory().setContents(getInventory().getContents());
	}

	@Override
	public Inventory getInventory() {
		if (darkThemeEnabled) {
			fillBlankSlotsWithItem(BLACK_GLASS_PANE);
		}

		Inventory inventory = Bukkit.createInventory(this, 9 * getRows(), name);
		itemMap.forEach((slot, menuItem) -> inventory.setItem(slot, menuItem.getItemStack()));

		return inventory;
	}

	public int getRows() {
		if (rows > 6) {
			rows = 6;
		} else if (rows < 1) {
			rows = 1;
		}

		return rows;
	}

	public void setItem(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> event) {
		itemMap.put(slot, new MenuItem(itemStack, event));
	}

	public void setItem(int slot, ItemStack itemStack) {
		setItem(slot, new MenuItem(itemStack));
	}

	public void setItem(int slot, MenuItem menuItem) {
		itemMap.put(slot, menuItem);
	}

	public void fillBlankSlotsWithItem(ItemStack itemStack) {
		int slots = 9 * rows;

		for (int i = 0; i < slots; i++) {
			MenuItem item = getItem(i);
			if (item == null) {
				setItem(i, itemStack);
			}
		}
	}


	public MenuItem getItem(int slot) {
		return itemMap.get(slot);
	}

	public void clearItems() {
		itemMap.clear();
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Menu getMenuByInventory(Inventory inventory) {
		InventoryHolder holder = inventory.getHolder();
		if (!(holder instanceof Menu)) {
			return null;
		}

		return (Menu) holder;
	}

	public Player getPlayer() {
		return player;
	}
}