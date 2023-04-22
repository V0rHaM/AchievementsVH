package me.v0rham.achievmentvh.confmanager;

import me.v0rham.achievmentvh.Main;
import me.v0rham.achievmentvh.extra.Extra;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ConfManager {

    static Main plugin = Main.getInstance();

    public static void createPlayerData(UUID uuid, String playerName) {
        plugin.getConfig().set(Settings.getPathPlayersData() + "." + uuid + ".name", playerName);
        plugin.getConfig().set(Settings.getPathPlayersData() + "." + uuid + ".points", 0);
        plugin.getConfig().set(Settings.getPathPlayersData() + "." + uuid + ".quest-done", new ArrayList<>());
        plugin.saveConfig();
        plugin.reloadConfig();
    }

    public static String questStatus(UUID uuid, String questName) {
        if (plugin.getConfig().getStringList(
                Settings.getPathPlayersData() + "." + uuid + ".quest-done").contains(questName)) return "&aВыполнено";
        return "&cНе выполнено";
    }

    public static boolean mathPlayerData(UUID uuid) {
        return Main.getInstance().getConfig().getString(Settings.getPathPlayersData() + "." + uuid) != null;
    }

    public static boolean findDoneQuest(UUID uuid, String questName) {
        return plugin.getConfig().getStringList(Settings.getPathPlayersData() + "." + uuid + ".quest-done").contains(questName);
    }

    public static void addPoints(UUID uuid, int points) {
        plugin.getConfig().set(Settings.getPathPlayersData() + "." + uuid + ".points", getPlayerPoints(uuid) + points);
        plugin.saveConfig();
        plugin.reloadConfig();
    }

    public static int getPlayerPoints(UUID uuid) {
        return plugin.getConfig().getInt(Settings.getPathPlayersData() + "." + uuid + ".points");
    }

    public static int getPriceQuestDone(String questName) {
        return plugin.getConfig().getInt(Settings.getPathQuestData() + "." + questName + ".points");
    }

    public static List<String> getAchievements(UUID uuid) {
        try {
            return plugin.getConfig().getStringList(Settings.getPathPlayersData() + "." + uuid + ".quest-done");
        } catch (NullPointerException exception) {
            plugin.getLogger().info("Error: " + exception);
        }
        return new ArrayList<>();
    }

    public static void addAchievement(Player player, String questName) {
        List<String> questDone = getAchievements(player.getUniqueId());
        questDone.add(questName);
        addPoints(player.getUniqueId(), getPriceQuestDone(questName));
        plugin.getConfig().set(Settings.getPathPlayersData() + "." + player.getUniqueId() + ".name", player.getName());
        plugin.getConfig().set(Settings.getPathPlayersData() + "." + player.getUniqueId() + ".quest-done", questDone);
        plugin.saveConfig();
        plugin.reloadConfig();
        player.sendMessage(Extra.getMsg(Settings.getPathQuestData() + "." + questName + ".text-done"));
    }

    public static void checkLineQuestDone(Player player, int line) {
//        for (String questInLine : Main.questMassive[line]) {
        for (String questName : plugin.getConfig().getConfigurationSection(Settings.getPathQuestData()).getKeys(false)) {
            if (plugin.getConfig().getInt(Settings.getPathQuestData() + "." + questName + ".line") != line) continue;
            if (!findDoneQuest(player.getUniqueId(), questName)) return;
        }
        addAchievement(player, "line" + line + "-done");
    }
}
