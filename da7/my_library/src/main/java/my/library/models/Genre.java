package my.library.models;

import java.io.Serializable;

public class Genre implements Serializable {
    private int genres_id;
    private String name;

    public Genre() {}

    public int getGenres_id() {
        return genres_id;
    }

    public void setGenres_id(int genres_id) {
        this.genres_id = genres_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
