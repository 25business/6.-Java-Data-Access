package my.library.database;

import org.jdbi.v3.core.Jdbi;

import java.io.FileNotFoundException;

public class JDBIManager {
    private static Jdbi instance;

    public static Jdbi get() throws FileNotFoundException {
        if(instance == null) {
            String connection_string = ConnectionString.create();
            instance = Jdbi.create(connection_string);
        }
        return instance;
    }
}
