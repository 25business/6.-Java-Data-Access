package my.library.hibernate.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "genres")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Genre{" +
                "genres_id=" + genres_id +
                ", name='" + name + '\'' +
                '}';
    }
}
