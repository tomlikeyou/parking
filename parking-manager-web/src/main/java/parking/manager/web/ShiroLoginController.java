package parking.manager.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import parking.common.*;
import parking.manager.service.IMenuService;
import parking.manager.service.IRoleService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author huang
 * @date 2020/1/13 14:47
 * @Disc
 **/
@Controller
public class ShiroLoginController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User user, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            try {
                subject.login(token);
            } catch (UnknownAccountException uae) {
                return new AjaxResult(ResultCode.LOGIN_FAIL, "can not find this account " + user.getUserName(), null);
            } catch (IncorrectCredentialsException ice) {
                return new AjaxResult(ResultCode.LOGIN_FAIL, "discorrect password", null);
            } catch (AuthenticationException e) {
                return new AjaxResult(ResultCode.LOGIN_FAIL, "unknown error has occured", null);
            }
        }

        List<Role> roles = roleService.getRolesByUserId(((User) subject.getPrincipal()).getUserId());
        List<Menu> menuList = new ArrayList<>();
        roles.stream().forEach(role -> {
            List<Menu> list = menuService.findMenusByRoleId(role.getRoleId());
            menuList.addAll(list);
        });
        ((User) subject.getPrincipal()).setMenuList(menuList);
        ((User) subject.getPrincipal()).setAuthorization(subject.getSession().getId());
        return (User) subject.getPrincipal();
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public Object unauthorizedMethod() {
        return new AjaxResult<>(ResultCode.LOGIN_FAIL, "current account has not been getAuthorized", null);
    }

}
