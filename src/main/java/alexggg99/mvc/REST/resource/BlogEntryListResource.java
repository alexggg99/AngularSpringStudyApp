package alexggg99.mvc.REST.resource;

import alexggg99.mvc.core.entities.Blog;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogEntryListResource extends ResourceSupport {

    private List<BlogEntryResource> list = new ArrayList<BlogEntryResource>();

    public List<BlogEntryResource> getList() {
        return list;
    }

    public void setList(List<BlogEntryResource> list) {
        this.list = list;
    }

}
