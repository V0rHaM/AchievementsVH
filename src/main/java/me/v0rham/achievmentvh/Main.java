package me.v0rham.achievmentvh;

import me.v0rham.achievmentvh.cashes.PlayerCash;
import me.v0rham.achievmentvh.cashes.QuestCash;
import me.v0rham.achievmentvh.confmanager.Settings;
import me.v0rham.achievmentvh.menu.MainMenu;
import me.v0rham.achievmentvh.menu.PersonalMenu;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static Main instance;
//    public static List<QuestCash> questCashes = new ArrayList<>();
//    public static List<PlayerCash> playerCashes = new ArrayList<>();
    public static String[][] questMassive = new String[6][6];

    @Override
    public void onEnable() {
        if (instance == null) instance = this;
        saveDefaultConfig();

        for (String questName : this.getConfig().getConfigurationSection(Settings.getPathQuestData()).getKeys(false)) {
            if (this.getConfig().getInt(Settings.getPathQuestData() + "." + questName + ".column") == 0) continue;
            questMassive[this.getConfig().getInt(Settings.getPathQuestData() + "." + questName + ".line") - 1]
                    [this.getConfig().getInt(Settings.getPathQuestData() + "." + questName + ".column") - 1] = questName;
        }

//        for (String line : this.getConfig().getConfigurationSection("Quest").getKeys(false)) {
//            for (String questName : this.getConfig().getConfigurationSection("Quest." + line).getKeys(false)) {
//                String displayName = this.getConfig().getString("Quest." + questName + ".display-name");
//                int points = this.getConfig().getInt("Quest." + questName + ".points");
//                int column = this.getConfig().getInt("Quest." + questName + ".column");
//
//                questCashes.add(new QuestCash(displayName, questName, points, line, column));
//            }
//        }
//        for (String playerUUID : this.getConfig().getConfigurationSection("Players").getKeys(false)) {
//            String playerName = this.getConfig().getString("Quest." + playerUUID + ".name");
//            int points = this.getConfig().getInt("Quest." + playerUUID + ".points");
//            List<String> questDone = this.getConfig().getStringList("Quest." + playerUUID + ".quest-done");
//
//            playerCashes.add(new PlayerCash(playerName, UUID.fromString(playerUUID), points, questDone));
//        }

        //start load timer
        long startTimer = System.currentTimeMillis();

        //register commands
        this.getCommand("menu").setExecutor(new MainMenu());
        this.getCommand("pm").setExecutor(new PersonalMenu());

        //register events
        this.getServer().getPluginManager().registerEvents(new Events(), this);
        this.getLogger().info("Время загрузки плагина: " + (System.currentTimeMillis() - startTimer) + "ms");
    }

    @Override
    public void onDisable() {
        if (instance != null) instance = null;
        saveConfig();
    }

    public static Main getInstance() {
        return instance;
    }
}
