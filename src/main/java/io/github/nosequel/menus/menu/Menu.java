package io.github.nosequel.menus.menu;

import io.github.nosequel.menus.MenuHandler;
import io.github.nosequel.menus.button.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class Menu {

    private final Player player;
    private final String title;
    private final int size;

    private final List<Button> buttons = new ArrayList<>();

    private Inventory inventory;

    /**
     * Constructor for creating a new Menu
     *
     * @param title the title
     * @param size  the size
     */
    public Menu(Player player, String title, int size) {
        this.player = player;
        this.title = title;
        this.size = size;
        this.registerMenu();
    }

    /**
     * Get the actual size of the menu
     *
     * @return the size
     */
    public int getSize() {
        return Math.min(this.size, 45);
    }

    /**
     * Update the menu
     */
    public void updateMenu() {
        this.updateMenu(this.getButtons());
    }

    /**
     * Update the menu with a certain list of buttons
     *
     * @param buttons the list of buttons
     */
    public void updateMenu(List<Button> buttons) {
        final Inventory inventory = this.inventory == null ? Bukkit.createInventory(null, this.getSize(), this.getTitle()) : this.inventory;

        this.registerMenu();
        this.clearMenu(inventory);

        buttons.stream()
                .filter(button -> button != null && button.toItemStack() != null)
                .forEach(button -> inventory.setItem(button.getIndex(), button.toItemStack()));

        if (inventory != this.inventory) {
            this.inventory = inventory;

            player.closeInventory();
            player.openInventory(inventory);
        } else {
            player.updateInventory();
        }
    }

    /**
     * Clear the contents of the menu
     */
    public void clearMenu(Inventory inventory) {
        if (inventory == null) {
            return; // menu doesn't exist yet.
        }

        for (int i = 0; i < this.size; i++) {
            inventory.setItem(i, new ItemStack(Material.AIR));
        }
    }

    /**
     * Executes the click action of an index
     *
     * @param clickType the type of click
     * @param index     the index where the click had been performed
     * @return whether the click event should be cancelled or not
     */
    public boolean click(ClickType clickType, int index) {
        final Optional<Button> button = this.getButtons().stream()
                .filter(current -> current.getIndex() == index)
                .findFirst();

        if (button.isPresent()) {
            return button.get().getClickAction().apply(clickType);
        }

        return false;
    }

    /**
     * Register a {@link Menu} to the map of menus
     *
     * @return the menu instance
     */
    public Menu registerMenu() {
        return MenuHandler.get().getMenus().put(this.getPlayer(), this);
    }

    /**
     * This method gets called whenever the player closes the inventory
     *
     * @param event the close event
     */
    public abstract void onClose(InventoryCloseEvent event);

}