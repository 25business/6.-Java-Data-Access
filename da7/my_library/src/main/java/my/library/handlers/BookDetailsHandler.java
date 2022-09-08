package my.library.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.hibernate.dao.BookDAO;
import my.library.templating.Renderer;
import my.library.utils.MainMenu;

import java.util.HashMap;

public class BookDetailsHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        int books_id = Integer.parseInt(context.pathParam("books_id"));
        HashMap<String, Object> modelData = new HashMap<>();
        modelData.put("main_menu", MainMenu.get());
        modelData.put("book", BookDAO.one(books_id));
        String html_content = Renderer.render("book_details.ftl", modelData);
        context.html(html_content);
    }
}
