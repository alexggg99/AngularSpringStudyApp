package alexggg99.mvc.REST.mvc;

import alexggg99.mvc.REST.resource.BlogEntryResource;
import alexggg99.mvc.REST.resource.asm.BlogEntryResourceAsm;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.BlogEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alexggg99 on 25.11.15.
 */

@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService blogEntryService;

    public BlogEntryController(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody BlogEntry test(@RequestBody BlogEntry entry){
        return entry;
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable long blogEntryId){
        BlogEntry entry = blogEntryService.find(blogEntryId);
        if(entry != null){
            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(@PathVariable Long blogEntryId){
        BlogEntry entry = blogEntryService.delete(blogEntryId);
        if(entry != null){
            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{blogEntryId}",
            method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResource> updateBlogEntry(@PathVariable Long blogEntryId,
                                                             @RequestBody BlogEntry blogEntry){
        BlogEntry entry = blogEntryService.update(blogEntryId, blogEntry);
        if(entry != null){
            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

}
