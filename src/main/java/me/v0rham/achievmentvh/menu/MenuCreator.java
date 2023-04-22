package me.v0rham.achievmentvh.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class MenuCreator {

    public static Inventory mainMenu = Bukkit.createInventory(null, 6 * 9, "Список игроков");
    public static Inventory personalMenu = Bukkit.createInventory(null, 6 * 9, "Задания");

    public static void createItem(Material material, int amount, Inventory inv, int Slot, String name, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);

        inv.setItem(Slot, item);
    }
    public static void createSkullItem(Player owner, int amount, Inventory inv, int Slot, String name, List<String> lore) {
        ItemStack skull = new ItemStack(Material.LEGACY_SKULL_ITEM, amount, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner.getName()));
        meta.setDisplayName(name);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        skull.setItemMeta(meta);

        inv.setItem(Slot, skull);
    }



//        createDisplay(Material.DIRT, 1, mainMenu, 0, "Земля ебать", lore);
//        createDisplay(Material.GOLD_BLOCK, 2, mainMenu, 5, "Золото ебать", lore);
//        createDisplay(Material.DIAMOND_BLOCK, 3, mainMenu, 8, "Алмаз ебать", lore);
}
