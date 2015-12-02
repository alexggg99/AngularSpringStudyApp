package alexggg99.mvc.REST.resource;

import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogResource extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog toBlog(){
        Blog entry = new Blog();
        entry.setTitle(title);
        return entry;
    }

}
