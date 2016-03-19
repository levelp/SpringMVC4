package mvc3.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class HomeController {

    /**
     * Аннотация @RequestMapping:
     *  * value - путь
     *  *
     * @param principal
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {
        return principal != null ?
                "home/homeSignedIn" :
                "home/homeNotSignedIn";
    }

}
