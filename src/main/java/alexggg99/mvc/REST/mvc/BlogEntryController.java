package alexggg99.mvc.REST.mvc;

import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.BlogEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alexggg99 on 25.11.15.
 */

@Controller
public class BlogEntryController {

    private BlogEntryService blogEntryService;

    public BlogEntryController(BlogEntryService blogEntryService) {
        this.blogEntryService = blogEntryService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody BlogEntry test(@RequestBody BlogEntry entry){
        return entry;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void get(){

    }

}
