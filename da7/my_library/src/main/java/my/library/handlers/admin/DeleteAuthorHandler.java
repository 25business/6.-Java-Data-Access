package my.library.handlers.admin;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import my.library.hibernate.dao.AuthorDAO;

public class DeleteAuthorHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String author_id = context.pathParam("author_id");
        try {
            AuthorDAO.delete(Integer.parseInt(author_id));
            context.redirect("/admin?deleteAuthor=true");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        context.redirect("/admin?deleteAuthor=false");
    }
}
