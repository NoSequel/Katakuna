package io.github.nosequel.menus.button;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.function.Function;

@Getter
public abstract class Button {

    private final MaterialData materialData;
    private final ItemMeta itemMeta;

    private int index;
    private Function<ClickType, Boolean> clickAction;

    /**
     * Constructor for making a new button object from a {@link ItemStack}
     *
     * @param index     the index of a button
     * @param itemStack the item stack
     */
    public Button(int index, ItemStack itemStack) {
        this.index = index;
        this.materialData = itemStack.getData();
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * Constructor for making a new button object
     *
     * @param index    the index of the button
     * @param material the material type of the button
     */
    public Button(int index, Material material) {
        this(index, new ItemStack(material));
    }

    /**
     * Method for setting the display name of a button
     *
     * @param displayName the display name
     */
    public void setDisplayName(String displayName) {
        this.itemMeta.setDisplayName(displayName);
    }

    /**
     * Method for setting the lore of a button
     *
     * @param lore the lore
     */
    public void setLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
    }

    /**
     * Set the click action of the button
     *
     * @param action the click action
     */
    public void setClickAction(Function<ClickType, Boolean> action) {
        this.clickAction = action;
    }

    /**
     * Update the index of the button
     *
     * @param i the new index
     * @return the current button
     */
    public Button updateIndex(int i) {
        this.index = i;
        return this;
    }

    /**
     * Method for converting a button to a {@link ItemStack}
     *
     * @return the converted itemstack
     */
    public ItemStack toItemStack() {
        ItemStack itemStack = this.materialData.toItemStack();
        itemStack.setItemMeta(this.itemMeta);

        return itemStack;
    }

}