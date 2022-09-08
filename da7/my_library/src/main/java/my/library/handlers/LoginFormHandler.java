package my.library.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.templating.Renderer;

public class LoginFormHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        context.html(Renderer.render("login.ftl"));
    }
}
