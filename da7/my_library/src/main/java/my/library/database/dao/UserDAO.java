package my.library.database.dao;

import my.library.database.JDBIManager;
import my.library.models.User;
import org.jdbi.v3.core.Jdbi;

import java.io.FileNotFoundException;

public class UserDAO {

    public static User for_username(String username) throws FileNotFoundException {
        Jdbi jdbi = JDBIManager.get();
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM users WHERE username = :username;")
                    .bind("username", username)
                    .mapToBean(User.class)
                    .findFirst().orElse(null);
        });
    }
}
