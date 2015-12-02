package alexggg99.mvc.REST.mvc;

import alexggg99.mvc.REST.resource.BlogEntryResource;
import alexggg99.mvc.REST.resource.BlogListResource;
import alexggg99.mvc.REST.resource.BlogResource;
import alexggg99.mvc.REST.resource.asm.BlogEntryResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogListResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogResourceAsm;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.BlogEntryService;
import alexggg99.mvc.core.services.BlogService;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alexggg99 on 25.11.15.
 */

@Controller
@RequestMapping("/rest/blogs")
public class BlogController {

    private BlogService blogService;

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

//    @RequestMapping(value="/{blogEntryId}",
//            method = RequestMethod.PUT)
//    public ResponseEntity<BlogEntryResource> updateBlogEntry(@PathVariable Long blogEntryId,
//                                                             @RequestBody BlogEntry blogEntry){
//        BlogEntry entry = blogEntryService.update(blogEntryId, blogEntry);
//        if(entry != null){
//            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(entry);
//            return new ResponseEntity<BlogEntryResource>(resource, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
//        }
//    }

}
