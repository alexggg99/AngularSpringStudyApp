package alexggg99.mvc.REST.resource.asm;

import alexggg99.mvc.REST.mvc.AccountController;
import alexggg99.mvc.REST.mvc.BlogController;
import alexggg99.mvc.REST.resource.BlogResource;
import alexggg99.mvc.core.entities.Blog;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResource> {

    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource res = new BlogResource();
        res.setTitle(blog.getTitle());
        res.add(linkTo(methodOn(BlogController.class).getBlog(blog.getId())).withSelfRel());
        res.add(linkTo(BlogController.class).slash(blog.getId()).slash("blog-entries").withRel("entries"));
        if(blog.getOwner() != null){
            res.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        }
        return res;
    }
}
