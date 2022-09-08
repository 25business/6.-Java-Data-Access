package my.library.handlers;

import com.google.common.hash.Hashing;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.hibernate.dao.UserDAO;
import my.library.exceptions.InvalidUsernamePasswordException;
import my.library.hibernate.models.User;
import my.library.hibernate.models.UserType;

import java.nio.charset.StandardCharsets;

public class LoginSubmissionHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String username = context.formParam("username");
        String password = context.formParam("password");
        try {
            User user = UserDAO.for_username(username);
            if(user == null)
                throw new InvalidUsernamePasswordException();
            password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            if(!password.equals(user.getPassword()))
                throw new InvalidUsernamePasswordException();

            context.sessionAttribute("user", user);
            if(user.getUser_type().equals(UserType.administrator)) context.redirect("/admin");
            else if(user.getUser_type().equals(UserType.customer)) context.redirect("/"); // vi smislite

        }catch (InvalidUsernamePasswordException ex) {
            context.html(ex.getMessage());
        }

    }
}
