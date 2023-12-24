package lc.core.utilidades;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
 
public class IconMenu implements Listener {
 
    private String name;
    private int size;
    private OptionClickEventHandler handler;
    private Plugin plugin;
    private Inventory inv = null;
    private String[] optionNames;
    private ItemStack[] optionIcons;
    private boolean destroy = false;
    
   
    public IconMenu(String name, int size, OptionClickEventHandler handler, Plugin plugin) {
        this.name = name;
        this.size = size;
        this.handler = handler;
        this.plugin = plugin;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        inv = Bukkit.createInventory(null, size, name);
    }
    
    public IconMenu(String name, int size, OptionClickEventHandler handler, Plugin plugin, boolean destroy) {
        this.name = name;
        this.size = size;
        this.handler = handler;
        this.plugin = plugin;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
        this.destroy = destroy;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        inv = Bukkit.createInventory(null, size, name);
    }
   
    public IconMenu setOption(int position, ItemStack icon, String name, String... info) {
        optionNames[position] = name;
        optionIcons[position] = setItemNameAndLore(icon, name, info);
        inv.setItem(position, icon);
        return this;
    }
    
    public IconMenu setOption(int position, ItemStack icon) {
        optionNames[position] = icon.getItemMeta().getDisplayName();
        optionIcons[position] = icon;
        inv.setItem(position, icon);
        return this;
    }
   
    public void open(Player player) {
        player.openInventory(inv);
    }
   
    public void destroy() {
        HandlerList.unregisterAll(this);
        handler = null;
        plugin = null;
        optionNames = null;
        optionIcons = null;
        inv = null;
    }
    
    public void clear() {
        optionNames = new String[size];
        optionIcons = new ItemStack[size];
        inv.clear();
    }
   
    @EventHandler(priority=EventPriority.LOWEST)
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inv)) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            if (slot >= 0 && slot < size && optionNames[slot] != null) {
                OptionClickEvent e = new OptionClickEvent((Player)event.getWhoClicked(), slot, optionNames[slot], this);
                handler.onOptionClick(e);
                if (e.willClose()) {
                    final Player p = (Player)event.getWhoClicked();
                    p.closeInventory();
                }
                if (e.willDestroy()) {
                    destroy();
                }
            }
        }
    }
    
    @EventHandler(priority=EventPriority.LOWEST)
    void onInventoryClick(InventoryCloseEvent event) {
        if (event.getInventory().equals(inv)) {
        	if(destroy)
        		destroy();
        }
    }
    
    @EventHandler(priority=EventPriority.LOWEST)
    void onInventoryClick(PlayerQuitEvent event) {
    	event.getPlayer().closeInventory();
    }
    
	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}
	
    public interface OptionClickEventHandler {
        public void onOptionClick(OptionClickEvent event);
    }
   
    public class OptionClickEvent {
        private Player player;
        private int position;
        private String name;
        private boolean close;
        private boolean destroy;
        private IconMenu iconmenu;
       
        public OptionClickEvent(Player player, int position, String name, IconMenu iconmenu) {
            this.player = player;
            this.position = position;
            this.name = name;
            this.close = true;
            this.destroy = false;
            this.iconmenu = iconmenu;
        }
       
        public Player getPlayer() {
            return player;
        }
       
        public int getPosition() {
            return position;
        }
       
        public String getName() {
            return name;
        }
       
        public boolean willClose() {
            return close;
        }
       
        public boolean willDestroy() {
            return destroy;
        }
       
        public void setWillClose(boolean close) {
            this.close = close;
        }
       
        public void setWillDestroy(boolean destroy) {
            this.destroy = destroy;
        }

		public IconMenu getIconmenu() {
			return iconmenu;
		}

		public void setIconmenu(IconMenu iconmenu) {
			this.iconmenu = iconmenu;
		}
    }
   
    private ItemStack setItemNameAndLore(ItemStack item, String name, String[] lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        
        return item;
    }
   
}