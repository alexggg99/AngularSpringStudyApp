package alexggg99.mvc.REST.resource;

import alexggg99.mvc.core.entities.Blog;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogListResource extends ResourceSupport {

    private List<BlogResource> list = new ArrayList<BlogResource>();

    public List<BlogResource> getBlogs() {
        return list;
    }

    public void setBlogs(List<BlogResource> list) {
        this.list = list;
    }

}
