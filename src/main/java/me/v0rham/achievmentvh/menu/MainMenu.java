package me.v0rham.achievmentvh.menu;

import me.v0rham.achievmentvh.Main;
import me.v0rham.achievmentvh.confmanager.ConfManager;
import me.v0rham.achievmentvh.confmanager.Settings;
import me.v0rham.achievmentvh.extra.Extra;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("achievements.admin")) {
            sender.sendMessage("Недостаточно прав!");
            return true;
        }
        if (!(sender instanceof Player)) return senderConsole();

        Player player = ((Player) sender).getPlayer();
        return openMainMenu(player);
    }


    private static boolean senderConsole() {
        if (Bukkit.getOnlinePlayers().size() <= 0) {
            Main.getInstance().getLogger().warning("Сервер пуст!");
            return true;
        }
        Main.getInstance().getLogger().info("Кол-во очков:");
        int step = 1;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Main.getInstance().getLogger().info(step + "." + player.getName() + " - " + pointsCounter(player));
            step++;
        }
        return true;
    }

    private static boolean openMainMenu(Player player) {
        int step = 0;
        MenuCreator.mainMenu.clear();

        for (Player playerList : Bukkit.getOnlinePlayers()) {
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(ChatColor.GREEN + "Кол-во очков: " + pointsCounter(playerList));
            lore.add(ChatColor.GREEN + "Заданий выполнено: " + questDoneCounter(playerList));
            lore.add("");
            for (String questName : ConfManager.getAchievements(playerList.getUniqueId())) {
                if (questName.length() <= 1) continue;
                if (!Main.getInstance().getConfig().getStringList(
                        Settings.getPathPlayersData() + "." + player.getUniqueId() + ".quest-done").contains(questName)) continue;

                lore.add(Extra.color("&7- " + Main.getInstance().getConfig().getString(Settings.getPathQuestData() + "." + questName + ".display-name")));
            }
            MenuCreator.createSkullItem(playerList, 1, MenuCreator.mainMenu, step, playerList.getName(), lore);
            step++;
        }
        player.openInventory(MenuCreator.mainMenu);
        return true;
    }

    private static int pointsCounter(Player player) {
        try {
            return ConfManager.getPlayerPoints(player.getUniqueId());
        } catch (NullPointerException exception) {
            Main.getInstance().getLogger().warning("Exception: " + exception);
            return 0;
        }
    }

    private static int questDoneCounter(Player player) {
        try {
            return ConfManager.getAchievements(player.getUniqueId()).size();
        } catch (NullPointerException exception) {
            Main.getInstance().getLogger().warning("Exception: " + exception);
            return 0;
        }
    }
}

