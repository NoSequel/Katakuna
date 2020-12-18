package io.github.nosequel.menus.button.pagination;

import io.github.nosequel.menus.button.Button;
import io.github.nosequel.menus.menu.PaginatedMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;

import java.util.function.Function;

public class NextButton extends Button {

    private final PaginatedMenu menu;

    /**
     * Constructor for making a new next button object
     * The constructor calls the super method from {@link Button}
     *
     * @param menu the menu instance which it's made from
     */
    public NextButton(PaginatedMenu menu) {
        super(8, Material.CARPET);
        this.menu = menu;
        this.setDisplayName(ChatColor.GRAY + "Next Button");
    }

    @Override
    public Function<ClickType, Boolean> getClickAction() {
        return type -> {
            menu.setPage(menu.getPage() + 1);
            menu.updateMenu();
            return true;
        };
    }
}