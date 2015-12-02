package alexggg99.mvc.REST.resource.asm;

import alexggg99.mvc.REST.mvc.BlogController;
import alexggg99.mvc.REST.resource.BlogListResource;
import alexggg99.mvc.REST.resource.BlogResource;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource res = new BlogListResource();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }
}
