package my.library.handlers.admin;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.hibernate.models.User;
import my.library.hibernate.models.UserType;

public class AdminAuthorisationHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        if( context.sessionAttribute("user") == null)
            context.redirect("/login");
        User user = (User)context.sessionAttribute("user");
        if(!user.getUser_type().equals(UserType.administrator))
            context.redirect("/login");
    }
}
