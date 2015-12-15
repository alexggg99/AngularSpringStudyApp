package alexggg99.mvc.REST.mvc;

import alexggg99.mvc.core.entities.BlogEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alexggg99 on 15.12.15.
 */

@Controller
@RequestMapping("/")
public class DefaultController {

    @RequestMapping
    public String test(){
        return "index";
    }

}
