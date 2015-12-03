package alexggg99.mvc.REST.resource.asm;

import alexggg99.mvc.REST.mvc.BlogController;
import alexggg99.mvc.REST.resource.BlogEntryListResource;
import alexggg99.mvc.REST.resource.BlogEntryResource;
import alexggg99.mvc.REST.resource.BlogListResource;
import alexggg99.mvc.core.services.util.BlogEntryList;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {

    public BlogEntryListResourceAsm() {
        super(BlogController.class, BlogEntryListResource.class);
    }

    @Override
    public BlogEntryListResource toResource(BlogEntryList list) {
        List<BlogEntryResource> resources = new BlogEntryResourceAsm().toResources(list.getEntries());
        BlogEntryListResource listResource = new BlogEntryListResource();
        listResource.setList(resources);
        listResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
        return listResource;
    }
}
