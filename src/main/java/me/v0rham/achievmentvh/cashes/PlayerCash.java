package me.v0rham.achievmentvh.cashes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerCash {

    private static String name;
    private static UUID uuid;
    private static int points;
    private static List<String> questDone = new ArrayList<>();

    public PlayerCash(String name, UUID uuid, int points) {
        PlayerCash.name = name;
        PlayerCash.uuid = uuid;
        PlayerCash.points = points;
    }

    public PlayerCash(String name, UUID uuid, int points, List<String> questDone) {
        PlayerCash.name = name;
        PlayerCash.uuid = uuid;
        PlayerCash.points = points;
        if (PlayerCash.questDone.containsAll(questDone)) {
            PlayerCash.questDone.addAll(questDone);
        }
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PlayerCash.name = name;
    }

    public static UUID getUuid() {
        return uuid;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points) {
        PlayerCash.points = points;
    }

    public static List<String> getQuestDone() {
        return questDone;
    }

    public static void setQuestDone(List<String> questDone) {
        PlayerCash.questDone = questDone;
    }
}
