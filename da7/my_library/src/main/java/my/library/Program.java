package my.library;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import my.library.handlers.*;
import my.library.handlers.admin.*;

public class Program {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles(System.getenv("JAVA_RESOURCES") + "/my_library/static", Location.EXTERNAL);
        });

        app.get("/", new HomeHandler());
        app.get("/books/{books_id}", new BookDetailsHandler());
        app.get("/authors", new AuthorsHandler());
        app.get("/login", new LoginFormHandler());
        app.post("/login", new LoginSubmissionHandler());

        app.before("/admin", new AdminAuthorisationHandler());
        app.before("/admin/*", new AdminAuthorisationHandler());
        app.get("/admin", new AdminDashboardHandler());
        app.post("/admin/author/new", new NewAuthorFormHandler());
        app.get("/admin/author/delete/{author_id}", new DeleteAuthorHandler());
        app.get("/admin/author/edit/{author_id}", new EditAuthorHandler());
        app.post("/admin/author/edit/{author_id}", new EditAuthorSubmitHandler());
        app.post("/admin/book/new", new NewBookSubmitHandler());

        app.start(9000);
    }
}
