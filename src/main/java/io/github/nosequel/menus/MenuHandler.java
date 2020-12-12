package io.github.nosequel.menus;

import io.github.nosequel.menus.listeners.ButtonListener;
import io.github.nosequel.menus.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class MenuHandler {

    private static MenuHandler instance;
    private final Map<Player, Menu> menus = new HashMap<>();

    /**
     * Constructor for creating a new MenuHandler instance
     *
     * @param plugin the plugin the listener gets registered to
     */
    public MenuHandler(JavaPlugin plugin) {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new ButtonListener(), plugin);
    }

    /**
     * Find a menu by a player
     *
     * @param player the player
     * @return the found menu
     */
    public Menu findMenu(Player player) {
        return this.menus.get(player);
    }

    /**
     * Get the current instance of the MenuHandler
     *
     * @return the instance
     */
    public static MenuHandler get() {
        return instance;
    }

}
