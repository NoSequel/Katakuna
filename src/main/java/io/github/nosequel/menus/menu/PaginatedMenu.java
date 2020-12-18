package io.github.nosequel.menus.menu;

import io.github.nosequel.menus.button.Button;
import io.github.nosequel.menus.button.pagination.NextButton;
import io.github.nosequel.menus.button.pagination.PreviousButton;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class PaginatedMenu extends Menu {

    private int page = 1;

    private final List<Button> paginationButtons = new ArrayList<Button>() {{
        this.add(new PreviousButton(PaginatedMenu.this));
        this.add(new NextButton(PaginatedMenu.this));
    }};

    /**
     * Constructor for creating a new PaginatedMenu {@link Menu}
     *
     * @param title the title
     * @param size  the size
     */
    public PaginatedMenu(Player player, String title, int size) {
        super(player, title, size + 9);
    }

    @Override
    public void updateMenu() {
        this.updateMenu(this.getButtonsInRange());
    }

    /**
     * Get all the buttons in the current range
     *
     * @return the buttons
     */
    public List<Button> getButtonsInRange() {
        final List<Button> buttons = this.getButtons().stream()
                .filter(button -> button.getIndex() >= ((page - 1) * this.getSize()) && button.getIndex() < (page * getSize()) - 9)
                .peek(button -> button.setIndex(button.getIndex() - ((getSize()) * (page - 1) - 9))).collect(Collectors.toList());

        buttons.addAll(this.paginationButtons);

        return buttons;
    }

    @Override
    public boolean click(ClickType clickType, int index) {
        final Optional<Button> button = this.getButtonsInRange().stream()
                .filter(current -> current.getIndex() == index)
                .findFirst();

        if (button.isPresent()) {
            return button.get().getClickAction().apply(clickType);
        }

        return false;
    }

    /**
     * Set the current page the menu is at
     *
     * @param page the page
     */
    public void setPage(int page) {
        this.page = Math.max(1, page);
    }
}