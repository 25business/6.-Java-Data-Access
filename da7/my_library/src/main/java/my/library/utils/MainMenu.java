package my.library.utils;


import java.util.ArrayList;

public class MainMenu {
    public static ArrayList<MenuItem> get() {
        ArrayList<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Home", "/"));
        items.add(new MenuItem("About", "/about"));
        items.add(new MenuItem("Authors", "/authors"));
        items.add(new MenuItem("Genres", "/genres"));
        items.add(new MenuItem("Advanced Search", "/advanced-search"));
        items.add(new MenuItem("Contact", "/contact"));
        return items;
    }
}
