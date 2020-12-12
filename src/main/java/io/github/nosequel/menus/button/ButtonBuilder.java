package io.github.nosequel.menus.button;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

@Getter
public class ButtonBuilder extends Button {

    private int index;

    /**
     * Constructor for making a new button builder object from a {@link ItemStack}
     * Calls super constructor from {@link Button}
     *
     * @param index     the index of the button
     * @param itemStack the itemstack of the button
     */
    public ButtonBuilder(int index, ItemStack itemStack) {
        super(index, itemStack);
    }

    /**
     * Constructor for making a new button builder object
     * Calls super constructor from {@link Button}
     *
     * @param index    the index of the button
     * @param material the material of the button
     */
    public ButtonBuilder(int index, Material material) {
        super(index, material);
    }

    /**
     * Constructor for making a new button builder object from a {@link Button}
     *
     * @param index  the index of the button
     * @param button the button to use to make a new button builder object
     */
    public ButtonBuilder(int index, Button button) {
        super(index, button.toItemStack());
    }

    /**
     * Set the index of the button
     *
     * @param index the new index of the button
     * @return the current button builder instance
     */
    public ButtonBuilder index(int index) {
        this.index = index;
        return this;
    }

    /**
     * Set the display name of the button
     *
     * @param displayName the display name
     * @return the current button builder instance
     */
    public ButtonBuilder displayName(String displayName) {
        super.setDisplayName(displayName);
        return this;
    }

    /**
     * Set the cancel click value of the super boolean
     *
     * @param clickAction the new value
     * @return the current button builder instance
     */
    public ButtonBuilder action(Function<ClickType, Boolean> clickAction) {
        super.setClickAction(clickAction);
        return this;
    }

    /**
     * Set the lore of the button
     *
     * @param lore the lore
     * @return the current button builder instance
     */
    public ButtonBuilder lore(String... lore) {
        super.setLore(lore);
        return this;
    }
}