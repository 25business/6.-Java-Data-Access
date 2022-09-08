package my.library.handlers.admin;

import com.github.slugify.Slugify;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import my.library.hibernate.dao.AuthorDAO;
import my.library.hibernate.models.Author;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDate;

public class EditAuthorSubmitHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String author_id = context.pathParam("author_id");
        Author author = AuthorDAO.one(Integer.parseInt(author_id));
        author.setFull_name(context.formParam("full_name"));
        String final_filename = null;
        UploadedFile photo = context.uploadedFile("photo");

        String authors_folder = System.getenv("JAVA_RESOURCES") + "/my_library/static/images/authors";
        if(photo.getFilename() != null && !photo.getFilename().equals("")) {
            try {
                Slugify slg = new Slugify();
                File file = new File(authors_folder + "/" +
                        slg.slugify(author.getFull_name()) + photo.getExtension());
                FileUtils.copyInputStreamToFile(photo.getContent(), file);
                final_filename = file.getName();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        author.setBiography(context.formParam("biography"));
        String born = context.formParam("born");
        String died = context.formParam("died");
        if(born != null && !born.equals(""))
            author.setBorn(LocalDate.parse(born));
        if(died != null && !died.equals(""))
            author.setDied(LocalDate.parse(died));
        if(final_filename != null) {
            author.setPhoto(final_filename);
        }

        try {
            AuthorDAO.update(author);
            context.redirect("/admin?updateAuthor=true");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        context.redirect("/admin?updateAuthor=false");
    }
}
