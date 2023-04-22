package me.v0rham.achievmentvh;

import me.v0rham.achievmentvh.confmanager.ConfManager;
import me.v0rham.achievmentvh.confmanager.Settings;
import me.v0rham.achievmentvh.menu.MenuCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!ConfManager.mathPlayerData(event.getPlayer().getUniqueId()))
            ConfManager.createPlayerData(event.getPlayer().getUniqueId(), event.getPlayer().getName());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        Inventory inventory = event.getInventory();
        if (inventory.equals(MenuCreator.mainMenu)
                || inventory.equals(MenuCreator.personalMenu)) event.setCancelled(true);
    }

    @EventHandler
    public void questArmorEvent(InventoryClickEvent event) {
        String[] armorTypeEng = new String[]{"leather-armor", "iron-armor", "golden-armor", "diamond-armor", "netherite-armor"};
        String[] armorType = new String[]{"LEATHER", "IRON", "GOLDEN", "DIAMOND", "NETHERITE"};
        int armorSelect = 5;
        Player player = (Player) event.getWhoClicked();
        ItemStack[] armorContents = player.getInventory().getArmorContents();

        int step = 0;
        for (String questTag : armorType) {
            armorSelect = hasFullArmorSet(armorContents, questTag, step);
            if (armorSelect < 5) break;
            step++;
        }

        if (armorSelect >= 5) return;
        if (ConfManager.findDoneQuest(player.getUniqueId(), armorTypeEng[armorSelect])) return;

        ConfManager.addAchievement(player, armorTypeEng[armorSelect]);
        ConfManager.checkLineQuestDone(player, Main.getInstance().getConfig().getInt(Settings.getPathQuestData() + "." + armorTypeEng[armorSelect] + ".line"));
    }

    //leather armor
    private int hasFullArmorSet(ItemStack[] armorContents, String armorType, int armorSelect) {
        for (ItemStack item : armorContents) {
            if (item == null) return 5;
            if (!item.getType().name().contains(armorType)) return 5;
        }
        return armorSelect;
    }
}
