package parking.manager.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:14
 * Description:
 */
@RestController
public class UserController {

    @RequestMapping("/index")
    public String index() {
        return "success";
    }
}
