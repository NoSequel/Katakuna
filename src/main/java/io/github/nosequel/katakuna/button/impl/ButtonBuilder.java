package io.github.nosequel.katakuna.button.impl;

import io.github.nosequel.katakuna.button.Button;
import io.github.nosequel.katakuna.button.Callback;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ButtonBuilder implements Button {

    private Callback<ClickType, Player> action;
    private List<String> lore = new ArrayList<>();

    private Material material;
    private ItemStack itemStack;

    private String displayName = "";
    private byte data = 0;
    private int index = 0;
    private int amount = 0;


    /**
     * Constructor for creating a new ButtonBuilder with default values
     *
     * @param material the material
     */
    public ButtonBuilder(Material material) {
        this.material = material;
        this.action = (type, player) -> {
        };
    }

    /**
     * Constructor for creating a new ButtonBuilder with the values pre set from a {@link Button}
     *
     * @param button the button
     */
    public ButtonBuilder(Button button) {
        this.material = button.getMaterial();
        this.action = button.getAction();
        this.lore = button.getLore();
        this.data = button.getData();
        this.amount = button.getAmount();
        this.displayName = button.getDisplayName();
        this.index = button.getIndex();
    }

    public ButtonBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Set the index of the Button
     *
     * @param index the new index
     * @return the current ButtonBuilder instance
     */
    public ButtonBuilder setIndex(int index) {
        this.index = index;

        return this;
    }

    /**
     * Set the display name of the Button
     *
     * @param displayName the new index
     * @return the current ButtonBuilder instance
     */
    public ButtonBuilder setDisplayName(String displayName) {
        this.displayName = displayName;

        return this;
    }

    /**
     * Set the material of the Button
     *
     * @param material the new index
     * @return the current ButtonBuilder instance
     */
    public ButtonBuilder setMaterial(Material material) {
        this.material = material;

        return this;
    }

    /**
     * Set the action of the Button
     *
     * @param action the new index
     * @return the current ButtonBuilder instance
     */
    public ButtonBuilder setAction(Callback<ClickType, Player> action) {
        this.action = action;

        return this;
    }

    /**
     * Set the lore of a Button
     *
     * @param lore the lore
     * @return the current ButtonBuilder instance
     */
    public ButtonBuilder setLore(String... lore) {
        this.lore = new ArrayList<>(Arrays.asList(lore));

        return this;
    }

    /**
     * Sets the data of a Button
     *
     * @param data the data
     * @return the current ButtonBuilder instance
     */
    public ButtonBuilder setData(byte data) {
        this.data = data;

        return this;
    }

    public ButtonBuilder setAmount(int amount) {
        this.amount = amount;

        return this;
    }

    /**
     * Change a Button into an ItemStack
     *
     * @return the item stack
     */
    public ItemStack toItemStack() {
        final ItemStack itemStack = new ItemStack(this.material, this.getAmount(), this.getData());
        final ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(this.getDisplayName());
        meta.setLore(this.getLore());

        itemStack.setItemMeta(meta);
        this.itemStack = itemStack;

        return itemStack;
    }
}