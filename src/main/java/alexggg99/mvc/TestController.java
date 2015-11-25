package alexggg99.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alexggg99 on 24.11.15.
 */

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "view";
    }
}
