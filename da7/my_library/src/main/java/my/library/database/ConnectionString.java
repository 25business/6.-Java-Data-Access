package my.library.database;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConnectionString {
    public static String create() throws FileNotFoundException {
        Gson gson = new Gson();
        String settings_path = System.getenv("JAVA_RESOURCES") + "/my_library/configuration/database.json";
        File settings_file = new File(settings_path);
        FileReader reader = new FileReader(settings_file);
        DBConfiguration conf = gson.fromJson(reader,DBConfiguration.class);

        StringBuilder connection_string = new StringBuilder();
        connection_string.append("jdbc:mysql://");
        if(conf.getUsername() != null) {
            connection_string.append(conf.getUsername());
            if(conf.getPassword() != null) {
                connection_string.append(":");
                connection_string.append(conf.getPassword());
            }
            connection_string.append("@");
        }
        if(conf.getHost() != null) {
            connection_string.append(conf.getHost());
        } else {
            connection_string.append("127.0.0.1");
        }
        if(conf.getPort() != null) {
            connection_string.append(":");
            connection_string.append(conf.getPort());
        }
        connection_string.append("/");
        connection_string.append(conf.getDatabase());
        if(conf.getServerTimezone() != null) {
            connection_string.append("?");
            connection_string.append("serverTimezone=");
            connection_string.append(conf.getServerTimezone());
        }

        return connection_string.toString();
    }
}
