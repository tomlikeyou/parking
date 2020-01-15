package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.AjaxResult;
import parking.common.ResultCode;
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
        System.out.println(user);
        int flag = userService.save(user);
        return flag > 0 ? new AjaxResult<>(ResultCode.SUCCESS, "success", null) : new AjaxResult<>(ResultCode.FAIL, "fail", null);
    }

    @PutMapping("/user")
    public Object modify(@RequestBody User user) {
        int flag = userService.modify(user);
        return null;
    }

    @DeleteMapping("/user/{userId}")
    public Object delete(@PathVariable("userId") Integer userId) {
        int flag = userService.delete(userId);
        return flag > 0 ? new AjaxResult<>(ResultCode.SUCCESS, "success", null) : new AjaxResult<>(ResultCode.FAIL, "fail", null);
    }
}
