package io.github.nosequel.katakuna.listeners;

import io.github.nosequel.katakuna.MenuHandler;
import io.github.nosequel.katakuna.button.Button;
import io.github.nosequel.katakuna.menu.Menu;
import io.github.nosequel.katakuna.menu.paginated.PaginatedMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;
import java.util.stream.Collectors;

public class ButtonListener implements Listener {

    private final MenuHandler menuHandler = MenuHandler.get();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Menu menu = menuHandler.findMenu(player);

        if (menu != null && event.getCurrentItem() != null) {
            final List<Button> buttons = menu instanceof PaginatedMenu ? ((PaginatedMenu) menu).getButtonsInRange() : menu.getButtons();
            final List<Button> $buttons = buttons.stream()
                    .filter(button -> button.getIndex() == event.getSlot())
                    .collect(Collectors.toList());

            if (!$buttons.isEmpty()) {
                event.setCancelled(true);
                $buttons.forEach(button -> button.getAction().accept(event.getClick(), player));
            }
        }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        final Menu menu = menuHandler.findMenu(player);

        if (menu != null) {
            menu.onClose(event);
            menuHandler.getMenus().remove(menu);
        }
    }
}