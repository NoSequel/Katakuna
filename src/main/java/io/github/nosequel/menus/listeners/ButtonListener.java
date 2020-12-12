package io.github.nosequel.menus.listeners;

import io.github.nosequel.menus.MenuHandler;
import io.github.nosequel.menus.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ButtonListener implements Listener {

    private final MenuHandler menuHandler = MenuHandler.get();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Menu menu = menuHandler.findMenu(player);

        if (menu != null && event.getCurrentItem() != null && player.getOpenInventory().getTopInventory() != null) {
            event.setCancelled(menu.click(event.getClick(), event.getSlot()));
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        final Menu menu = menuHandler.findMenu(player);

        if (menu != null) {
            menu.onClose(event);
        }
    }
}