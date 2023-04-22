package me.v0rham.achievmentvh.extra;

import me.v0rham.achievmentvh.Main;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Extra {

    public static String getMsg(String path) {
        return color(Main.getInstance().getConfig()
                .getString(path,"&cError: &eText not found. &cError#ExtraF.getMsgM.11s.13e"));
                                                                // F = File, M = method s = start line, e = end line
    }

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> colorList(List<String> list) {
        List<String> coloredList = new ArrayList<>();
        for (String text : list) {
            coloredList.add(color(text));
        }
        return coloredList;
    }
}
