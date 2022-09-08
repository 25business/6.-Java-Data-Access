package my.library.handlers.admin;

import com.github.slugify.Slugify;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import my.library.hibernate.dao.AuthorDAO;
import my.library.hibernate.models.Author;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;

public class NewAuthorFormHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        String full_name = context.formParam("full_name");
        String biography = context.formParam("biography");
        String born = context.formParam("born");
        String died = context.formParam("died");
        String final_filename = null;
        UploadedFile photo = context.uploadedFile("photo");

        String authors_folder = System.getenv("JAVA_RESOURCES") + "/my_library/static/images/authors";
        if(photo.getFilename() != null && !photo.getFilename().equals("")) {
            try {
                Slugify slg = new Slugify();
                File file = new File(authors_folder + "/" +
                        slg.slugify(full_name) + photo.getExtension());
                FileUtils.copyInputStreamToFile(photo.getContent(), file);
                final_filename = file.getName();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        HashMap<String, Object> author_data = new HashMap<>();
        if(born != null && !born.equals("")) author_data.put("born", born);
        if(died != null && !died.equals("")) author_data.put("died", died);
        if(biography != null && !biography.equals("")) author_data.put("biography", biography);
        if(final_filename != null) author_data.put("photo", final_filename);
        author_data.put("full_name", full_name);
        Author author = new Author(author_data);
        try {
           AuthorDAO.save(author);
           if(author.getAuthors_id() > 0) {
               context.redirect("/admin?saveAuthor=true");
               return;
           }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        context.redirect("/admin?saveAuthor=false");
    }
}
