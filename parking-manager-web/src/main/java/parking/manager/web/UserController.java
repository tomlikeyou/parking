package parking.manager.web;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/users")
    public Object getUsers() {
        return null;
    }

    @GetMapping("/user")
    public Object find() {
        return null;
    }

    @PostMapping("/user")
    public Object save() {
        return null;
    }

    @PutMapping("/user")
    public Object modify() {
        return null;
    }

    @DeleteMapping("/user/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        return null;
    }
}
