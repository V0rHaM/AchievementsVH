package me.v0rham.achievmentvh.cashes;

public class QuestCash {

    private static String name;
    private static String tag;
    private static int point;
    private static String line;
    private static int column;

    public QuestCash(String name, String tag, int point, String line, int column) {
        QuestCash.name = name;
        QuestCash.tag = tag;
        QuestCash.point = point;
        QuestCash.line = line;
        QuestCash.column = column;
    }


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        QuestCash.name = name;
    }

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        QuestCash.tag = tag;
    }

    public static int getPoint() {
        return point;
    }

    public static void setPoint(int point) {
        QuestCash.point = point;
    }

    public static String getLine() {
        return line;
    }

    public static void setLine(String line) {
        QuestCash.line = line;
    }

    public static int getColumn() {
        return column;
    }

    public static void setColumn(int column) {
        QuestCash.column = column;
    }
}
