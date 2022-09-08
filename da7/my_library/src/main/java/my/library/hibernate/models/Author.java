package my.library.hibernate.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

@Entity
@Table(name="authors")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authors_id;
    private String full_name;
    private String photo;
    private String biography;
    private LocalDate born;
    private LocalDate died;

    public Author() {}
    public Author(HashMap<String, Object> author_data) {
        if(author_data.containsKey("authors_id")) {
            this.authors_id = (int) author_data.get("authors_id");
        }
        if(author_data.containsKey("full_name")) {
            this.full_name = (String) author_data.get("full_name");
        }
        if(author_data.containsKey("biography")) {
            this.biography = (String) author_data.get("biography");
        }
        if(author_data.containsKey("born")) {
            if(author_data.get("born") instanceof String) {
                this.born = LocalDate.parse((String)author_data.get("born"));
            } else {
                this.born = (LocalDate) author_data.get("born");
            }
        }
        if(author_data.containsKey("died")) {
            if(author_data.get("died") instanceof String) {
                this.died = LocalDate.parse((String)author_data.get("died"));
            } else {
                this.died = (LocalDate) author_data.get("died");
            }
        }
        if(author_data.containsKey("photo")) {
            this.photo = (String) author_data.get("photo");
        } else {
            this.photo = "nophoto.webp";
        }
    }

    public int getAuthors_id() {
        return authors_id;
    }

    public void setAuthors_id(int authors_id) {
        this.authors_id = authors_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authors_id=" + authors_id +
                ", full_name='" + full_name + '\'' +
                ", photo='" + photo + '\'' +
                ", biography='" + biography + '\'' +
                ", born=" + born +
                ", died=" + died +
                '}';
    }
}
