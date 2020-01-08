package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.User;
import parking.manager.service.IUserService;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:14
 * Description:
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index() {
        return "success";
    }

    @GetMapping(value = "/users")
    public Object getUsers() {
        return null;
    }

    @GetMapping("/user/{userId}")
    public Object find(@PathVariable("userId") Integer userId) {
        User user = userService.getUserById(userId);
        return user;
    }

    @PostMapping("/user")
    public Object save(@RequestBody User user) {
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
