# Katukana
Light-weight and extensive Menu API made with the Spigot API

# Example Usage
``Creating a Menu``
```java
import io.github.nosequel.menus.menu.PaginatedMenu;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExampleMenu extends PaginatedMenu {
    
    public ExampleMenu(Player player) {
        super(player, "title", 9);
    }
 
    @Override
    public List<Button> getButtons() {        
        final List<Button> buttons = new ArrayList<>();

        for(int i = 0; i < 50; i++) {
            buttons.add(new ButtonBuilder(i, Material.COMPASS).displayName("Example Item").lore("Example Lore #" + i).action(type -> true));
        }

        return buttons;
    } 
    
    @Override
    public void onClose(InventoryCloseEvent event) {
        event.getPlayer().sendMessage("Closing inventory,,, sir,,,")
    }
    
}
```

# Notes
* You must register an instance of the  MenuHandler before you try to open/create any menu. 
* This is still a work-in-progress project, bugs may occur. 
