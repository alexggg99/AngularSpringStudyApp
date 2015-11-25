package alexggg99.mvc.REST.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogEntryResource extends ResourceSupport {

    private Long id;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Link getId() {
//        return id;
//    }

    public void setId(Long id) {
        this.id = id;
    }

}
