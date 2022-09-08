package my.library.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.hibernate.dao.BookDAO;
import my.library.templating.Renderer;
import my.library.utils.MainMenu;

import java.util.HashMap;

public class HomeHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        HashMap<String, Object> modelData = new HashMap<>();
        modelData.put("main_menu", MainMenu.get());
        modelData.put("books", BookDAO.all());
        String html_content = Renderer.render("home.ftl", modelData);
        context.html(html_content);
    }
}
