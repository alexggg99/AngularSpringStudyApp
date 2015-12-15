package alexggg99.mvc.REST.mvc;

import alexggg99.mvc.REST.Exceptions.NotFoundException;
import alexggg99.mvc.REST.resource.BlogEntryListResource;
import alexggg99.mvc.REST.resource.BlogEntryResource;
import alexggg99.mvc.REST.resource.BlogListResource;
import alexggg99.mvc.REST.resource.BlogResource;
import alexggg99.mvc.REST.resource.asm.BlogEntryListResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogEntryResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogListResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogResourceAsm;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.BlogEntryService;
import alexggg99.mvc.core.services.BlogService;
import alexggg99.mvc.core.services.Exceptions.BlogNotFoundException;
import alexggg99.mvc.core.services.util.BlogEntryList;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by alexggg99 on 25.11.15.
 */

@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value="/{blogId}", method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable long blogId){
        Blog entry = blogService.find(blogId);
        if(entry != null){
            BlogResource resource = new BlogResourceAsm().toResource(entry);
            return new ResponseEntity<BlogResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<BlogResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAll(){
        BlogList list = blogService.findAllBlogs();
        if(list != null){
            BlogListResource resource = new BlogListResourceAsm().toResource(list);
            return new ResponseEntity<BlogListResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<BlogListResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResource> createBlogEntry(@PathVariable Long blogId,
                                                            @RequestBody BlogEntryResource sentBlogEntry){
        try{
            BlogEntry entry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(entry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<BlogEntryResource>(resource, headers, HttpStatus.CREATED);
        }catch(BlogNotFoundException ex){
            throw new NotFoundException(ex);
        }
    }

    @RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.GET)
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId){
        try{
            BlogEntryList list = blogService.findAllBlogEntries(blogId);
            BlogEntryListResource res = new BlogEntryListResourceAsm().toResource(list);
            return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
        }catch(BlogNotFoundException ex){
            throw new NotFoundException(ex);
        }
    }

}
