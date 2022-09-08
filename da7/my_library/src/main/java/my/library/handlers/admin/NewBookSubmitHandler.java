package my.library.handlers.admin;

import com.github.slugify.Slugify;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import my.library.hibernate.HibernateUtil;
import my.library.hibernate.dao.AuthorDAO;
import my.library.hibernate.dao.BookDAO;
import my.library.hibernate.dao.PublisherDAO;
import my.library.hibernate.models.Book;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NewBookSubmitHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        try {
            List<String> book_authors_str = context.formParams("book_author");
            int publisher = Integer.parseInt(context.formParam("publisher"));
            List<Integer> book_authors_ids = new ArrayList<>();
            book_authors_str.forEach(str_id -> book_authors_ids.add(Integer.parseInt(str_id)));
            String title = context.formParam("title");
            Book book = new Book();
            book.setTitle(title);
            if(!context.formParam("release_date").equals("")) {
                book.setRelease_date(
                        LocalDate.parse(context.formParam("release_date"))
                );
            }
            book.setSynopsis(context.formParam("synopsis"));

            UploadedFile photo = context.uploadedFile("cover");

            String authors_folder = System.getenv("JAVA_RESOURCES") + "/my_library/static/images/covers";
            if(photo.getFilename() != null && !photo.getFilename().equals("")) {
                try {
                    Slugify slg = new Slugify();
                    File file = new File(authors_folder + "/" +
                            slg.slugify(title) + photo.getExtension());
                    FileUtils.copyInputStreamToFile(photo.getContent(), file);
                    book.setCover(slg.slugify(title) + photo.getExtension());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (publisher > 0) book.setPublisher(PublisherDAO.one(publisher));
            book.setAuthors(AuthorDAO.for_ids(book_authors_ids));
            BookDAO.save(book);

            HibernateUtil.closeAll();
            context.redirect("/admin?bookSaved=true");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        context.redirect("/admin?bookSaved=false");

    }
}
