package me.v0rham.achievmentvh.menu;

import me.v0rham.achievmentvh.Main;
import me.v0rham.achievmentvh.confmanager.ConfManager;
import me.v0rham.achievmentvh.confmanager.Settings;
import me.v0rham.achievmentvh.extra.Extra;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonalMenu implements CommandExecutor {

    static FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            Main.getInstance().getLogger().info("Эту команду может использовать только игрок!");
            return true;
        }
        Player player = ((Player) sender).getPlayer();
        return openPersonalMenu(player);
    }

    public static boolean openPersonalMenu(Player player) {
        int step = 0;
        MenuCreator.personalMenu.clear();

        for (String questName : config.getConfigurationSection(Settings.getPathQuestData()).getKeys(false)) {
            List<String> questStatus = config.getStringList(Settings.getPathQuestData() + "." + questName + ".lore");
            for (int i = 0; i < questStatus.size(); i++) {
                questStatus.set(i, questStatus.get(i).replaceAll("%quest_status%", ConfManager.questStatus(player.getUniqueId(), questName)));
            }

            MenuCreator.createItem(
                    Material.getMaterial(
                            config.getString(Settings.getPathQuestData() + "." + questName + ".icon", "STONE").toUpperCase(Locale.ROOT)),
                    1, MenuCreator.personalMenu, step,
                    Extra.color(config.getString(Settings.getPathQuestData() + "." + questName + ".display-name", "Not valid name")),
                    Extra.colorList(questStatus));
            step++;
        }
        MenuCreator.createItem(
                Material.RED_CONCRETE, 1, MenuCreator.personalMenu, 49,
                Extra.color("&fОчков набрано: " + ConfManager.getPlayerPoints(player.getUniqueId())), new ArrayList<>());
        player.openInventory(MenuCreator.personalMenu);
        return true;
    }
}
