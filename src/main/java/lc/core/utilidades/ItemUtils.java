/*
* Author: Luca Bosin / Wombosvideo
* License: Creative Commons -> More info [url]http://customitem.jimdo.de/[/url]
*/
package lc.core.utilidades;
 
import java.util.List;
 


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import com.google.common.collect.Lists;
 
public class ItemUtils extends ItemStack {
 
//Item with same specifications like choosen itemstack
public ItemUtils(ItemStack itemStack){
this.setType(itemStack.getType());
this.setAmount(itemStack.getAmount());
this.setDurability(itemStack.getDurability());
this.setItemMeta(itemStack.getItemMeta());
}
 
//Item with Material, DamageId, Amount, Display Name and Lores List
public ItemUtils(Material material, Short damageid, Integer amount, String displayName, List<String> lores){
this.setType(material);
this.setAmount(amount);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
im.setLore(lores);
this.setItemMeta(im);


}

//Item with Material, DamageId, Amount, Display Name and Lores List
public ItemUtils(Material material, Short damageid, Integer amount, String displayName, String loresSplit){
this.setType(material);
this.setAmount(amount);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
List<String> lr = Lists.newArrayList();
for(String lrs : loresSplit.split("=="))
	lr.add(lrs);
im.setLore(lr);
this.setItemMeta(im);


}

//Item with Material, DamageId, Amount, Display Name and Lores List
public ItemUtils(String skull_item_Owner, Integer amount, String displayName, String loresSplit){
this.setType(Material.SKULL_ITEM);
this.setAmount(amount);
this.setDurability((short) 3);
SkullMeta im = (SkullMeta) this.getItemMeta();
im.setDisplayName(displayName);
List<String> lr = Lists.newArrayList();
for(String lrs : loresSplit.split("=="))
	lr.add(lrs);
im.setLore(lr);
im.setOwner(skull_item_Owner);
this.setItemMeta(im);


}

//Item with Material, DamageId, Amount, Display Name and Lores List
public ItemUtils(String skull_item_Owner, Integer amount){
this.setType(Material.SKULL_ITEM);
this.setAmount(amount);
this.setDurability((short) 3);
SkullMeta im = (SkullMeta) this.getItemMeta();
im.setOwner(skull_item_Owner);
this.setItemMeta(im);


}
 
//Item with Material and DamageId
public ItemUtils(Material material, Short damageid){
this.setType(material);
this.setDurability((short) damageid);
this.setAmount(1);


}
 
//Item with Material and Amount
public ItemUtils(Material material, Integer amount){
this.setType(material);
this.setAmount(amount);


}
 
//Item with Material and Display Name
public ItemUtils(Material material, String displayName){
this.setType(material);
this.setAmount(1);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
this.setItemMeta(im);


}
 
//Item with Material and Lores List
public ItemUtils(Material material, List<String> lores){
this.setType(material);
this.setAmount(1);
ItemMeta im = this.getItemMeta();
im.setLore(lores);
this.setItemMeta(im);


}
 
//Item with Material, DamageId and Amount
public ItemUtils(Material material, Short damageid, Integer amount){
this.setType(material);
this.setAmount(amount);
this.setDurability((short) damageid);


}
 
//Item with Material, DamageId, Amount and Display Name
public ItemUtils(Material material, Short damageid, Integer amount, String displayName){
this.setType(material);
this.setAmount(amount);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
this.setItemMeta(im);


}
 
//Item with Material, DamageId, Amount, Display Name and Lores List
public ItemUtils(Material material, Short damageid, Integer amount, List<String> lores){
this.setType(material);
this.setAmount(amount);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setLore(lores);
this.setItemMeta(im);


}
 
//Item with Material, DamageId and Display Name
public ItemUtils(Material material, Short damageid, String displayName){
this.setType(material);
this.setAmount(1);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
this.setItemMeta(im);


}
 
//Item with Material, DamageId, Display Name and Lores List
public ItemUtils(Material material, Short damageid, String displayName, List<String> lores){
this.setType(material);
this.setAmount(1);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
im.setLore(lores);
this.setItemMeta(im);


}
 
//Item with Material, DamageId and Lores List
public ItemUtils(Material material, Short damageid, List<String> lores){
this.setType(material);
this.setAmount(1);
this.setDurability((short) damageid);
ItemMeta im = this.getItemMeta();
im.setLore(lores);
this.setItemMeta(im);


}
 
//Item with Material, Amount and Display Name
public ItemUtils(Material material, Integer amount, String displayName){
this.setType(material);
this.setAmount(amount);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
this.setItemMeta(im);


}
 
//Item with Material, DamageId, Amount, Display Name and Lores List
public ItemUtils(Material material, Integer amount, String displayName, List<String> lores){
this.setType(material);
this.setAmount(amount);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
im.setLore(lores);
this.setItemMeta(im);


}
 
//Item with Material, Amount and Lores List
public ItemUtils(Material material, Integer amount, List<String> lores){
this.setType(material);
this.setAmount(amount);
ItemMeta im = this.getItemMeta();
im.setLore(lores);
this.setItemMeta(im);


}
 
//Item with Material, Display Name and Lores List
public ItemUtils(Material material, String displayName, List<String> lores){
this.setType(material);
this.setAmount(1);
ItemMeta im = this.getItemMeta();
im.setDisplayName(displayName);
im.setLore(lores);
this.setItemMeta(im);


}
 
public ItemUtils getCustomItem(ItemStack itemStack){
this.setType(itemStack.getType());
this.setAmount(itemStack.getAmount());
this.setDurability(itemStack.getDurability());
this.setItemMeta(itemStack.getItemMeta());
return this;
}
 
public boolean isSameLike(ItemUtils customItem){
if(!(customItem.getType().equals(this.getType()))){
return false;
}
if(this.getItemMeta().hasDisplayName()){
if(!(customItem.getItemMeta().hasDisplayName())){
return false;
}else{
if(!(this.getItemMeta().getDisplayName().equals(customItem.getItemMeta().getDisplayName()))){
return false;
}
}
}
if(this.getItemMeta().hasLore()){
if(!(customItem.getItemMeta().hasLore())){
return false;
}
}
if(!(this.getItemMeta().getEnchants().equals(customItem.getItemMeta().getEnchants()))){
return false;
}
if(!(this.getDurability() == customItem.getDurability())){
return false;
}
return true;
}
 
public boolean isSameItemStackLike(ItemStack itemStack){
if(!(itemStack.getType().equals(this.getType()))){
return false;
}
if(this.getItemMeta().hasDisplayName()){
if(!(itemStack.getItemMeta().hasDisplayName())){
return false;
}else{
if(!(this.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName()))){
return false;
}
}
}
if(this.getItemMeta().hasLore()){
if(!(itemStack.getItemMeta().hasLore())){
return false;
}
}
if(!(this.getItemMeta().getEnchants().equals(itemStack.getItemMeta().getEnchants()))){
return false;
}
if(!(this.getDurability() == itemStack.getDurability())){
return false;
}
return true;
}
 
//Add the Item to a Player's Inventory
public void add(Player player){
player.getInventory().addItem(this);
}
 
//Add the Item to an Inventory
public void add(Inventory inventory){
inventory.addItem(this);
}
 
//Set the Item in a Player's Inventory on a specific slot
public void set(Player player, Integer slot){
player.getInventory().setItem(slot, this);
}
 
//Set the Item in an Inventory on a specific slot
public void set(Inventory inventory, Integer slot){
inventory.setItem(slot, this);
}
 
//Sets the Item as Helmet on a Player
public void setHelmet(Player p){
p.getInventory().setHelmet(this);
}
 
//Sets the Item as Chestplate on a Player
public void setChestplate(Player p){
p.getInventory().setChestplate(this);
}
 
//Sets the Item as Leggings on a Player
public void setLeggings(Player p){
p.getInventory().setLeggings(this);
}
 
//Sets the Item as Boots on a Player
public void setBoots(Player p){
p.getInventory().setBoots(this);
}
 
//Sets the Item as PlayerInHandItem
public void setInHand(Player p){
p.getInventory().setItemInHand(this);
}
 
//Drops the Item on the specified Location
public void dropItem(Location loc){
loc.getWorld().dropItem(loc, this);
}
 
//Drops the Item naturally on the specified Location
public void dropItemNaturally(Location loc){
loc.getWorld().dropItemNaturally(loc, this);
}
 
}