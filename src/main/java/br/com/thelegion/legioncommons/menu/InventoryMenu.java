package br.com.thelegion.legioncommons.menu;

import br.com.thelegion.legioncommons.menu.button.ItemButton;
import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;

@Getter
public abstract class InventoryMenu implements InventoryHolder {

	static final ItemStack BLANK_ITEM = new ItemStack(Material.AIR);

	private final String title;
	private final int rows;

	private final Inventory inventory;
	private final Map<Integer, ItemButton> buttonPositionMap;
	private final Map<String, Object> metadataMap;

	public InventoryMenu(String title, int rows) {
		Preconditions.checkArgument(rows <= 6, "Inventarios só suportam até 6 linhas!");

		this.title = title;
		this.rows = rows;
		this.inventory = Bukkit.createInventory(this, 9 * rows, title);
		this.buttonPositionMap = new WeakHashMap<>();
		this.metadataMap = new WeakHashMap<>();
	}

	protected void generate() {
		buttonPositionMap.forEach((slot, button) -> {
			ItemStack itemStack = button.getItemStack();
			if (itemStack != null) {
				inventory.setItem(slot, itemStack);
			} else {
				inventory.setItem(slot, BLANK_ITEM);
			}
		});
	}

	public void set(int slot, ItemButton button) {
		Preconditions.checkArgument(slot <= 9 * rows, "Inventarios só suportam até 6 linhas!");

		buttonPositionMap.remove(slot);
		if (button != null && button.getItemStack() != null) {
			buttonPositionMap.put(slot, button);
		}

		generate();
	}

	public void set(int slot, ItemStack stack, BiConsumer<Player, ItemStack> callback) {
		set(slot, ItemButton.builder()
			.itemStack(stack)
			.action(callback)
			.build());
	}

	public void set(int slot, ItemStack stack) {
		set(slot, stack, null);
	}

	public void remove(int slot) {
		set(slot, ItemButton.EMPTY);
	}

	public void clear() {
		inventory.clear();
	}

	public void open(Player player) {
		generate();

		player.openInventory(inventory);
	}

	public ItemStack getItem(int slot) {
		ItemButton itemButton = buttonPositionMap.get(slot);
		if (itemButton != null) {
			return itemButton.getItemStack();
		}

		return inventory.getItem(slot);
	}

	public void onClose(Player player) {
		// dummy
	}

	public void onTickUpdate(Player player) {
		// dummy
	}

	void handleClickEvent(InventoryClickEvent event) {
		if (!event.getClickedInventory().equals(inventory)) return;
		if (!(event.getWhoClicked() instanceof Player)) return;

		Player player = (Player) event.getWhoClicked();


		ItemButton itemButton = buttonPositionMap.get(event.getRawSlot());
		if (itemButton == null) {
			return;
		}

		ItemStack itemStack = itemButton.getItemStack();
		if (itemStack == null) {
			return;
		}

		BiConsumer<Player, ItemStack> action = itemButton.getAction();
		if (action != null) {
			action.accept(player, itemStack);
		}

		event.setCancelled(true);
	}
}
