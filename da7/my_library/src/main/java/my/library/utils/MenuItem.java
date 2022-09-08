package my.library.utils;

public class MenuItem {
    private String title;
    private String path;
    private boolean isExternal;

    public MenuItem(String title, String path) {
        this.title = title;
        this.path = path;
        this.isExternal = false;
    }

    public MenuItem(String title, String path, boolean isExternal) {
        this.title = title;
        this.path = path;
        this.isExternal = isExternal;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public boolean isExternal() {
        return isExternal;
    }
}
