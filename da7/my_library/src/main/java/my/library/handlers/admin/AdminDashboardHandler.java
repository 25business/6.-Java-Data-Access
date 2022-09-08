package my.library.handlers.admin;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.hibernate.dao.AuthorDAO;
import my.library.hibernate.dao.PublisherDAO;
import my.library.hibernate.models.Publisher;
import my.library.templating.Renderer;

import java.util.HashMap;

public class AdminDashboardHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        HashMap<String, Object> modelData = new HashMap<>();
        if(context.queryParam("saveAuthor") != null) {
            modelData.put("saveAuthor", context.queryParam("saveAuthor"));
        }
        if(context.queryParam("deleteAuthor") != null) {
            modelData.put("deleteAuthor", context.queryParam("deleteAuthor"));
        }

        if(context.queryParam("updateAuthor") != null) {
            modelData.put("updateAuthor", context.queryParam("updateAuthor"));
        }
        modelData.put("authors", AuthorDAO.all());
        modelData.put("publishers", PublisherDAO.all());

        context.html(Renderer.render("admin/dashboard.ftl", modelData));
    }
}
