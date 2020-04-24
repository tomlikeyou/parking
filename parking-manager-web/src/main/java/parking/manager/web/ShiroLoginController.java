package parking.manager.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public Object login(@RequestBody User user) {
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
        //菜单树
        List<Menu> menuList = new ArrayList<>();
        Set<String> menuNameSet = new HashSet<>();
        roles.stream().forEach(role -> {
            menuList.addAll(menuService.findMenusByRoleId(role.getRoleId()));
            menuNameSet.addAll(menuService.findMenuNamesByRoleId(role.getRoleId()));
        });
        Set<String> permsSet = new HashSet<>();
        roles.stream().forEach((role) -> {
            permsSet.addAll(menuService.findPermsByRoleId(role.getRoleId()));
        });
        //设置菜单树
        ((User) subject.getPrincipal()).setMenuList(menuList);
        //返回sessionid
        ((User) subject.getPrincipal()).setAuthorization(subject.getSession().getId());
        //返回按钮权限集
        ((User) subject.getPrincipal()).setPermList(permsSet);
        //返回页面权限集
        ((User) subject.getPrincipal()).setPagePerms(menuNameSet);

        return (User) subject.getPrincipal();
    }

    @RequestMapping("/unauthorized")
    @ResponseBody
    public Object unauthorizedMethod() {
        return new AjaxResult<>(ResultCode.LOGGIN_TIMEOUT, ResultCode.findMessageByCode(ResultCode.LOGGIN_TIMEOUT), null);
    }

    @RequestMapping("/unAuth")
    @ResponseBody
    public Object unAuth() {
        return new AjaxResult<>(ResultCode.UNAUTH, ResultCode.findMessageByCode(ResultCode.UNAUTH), null);
    }

    @GetMapping("/logout")
    @ResponseBody
    public Object logout() {
        SecurityUtils.getSubject().logout();
        return AjaxResultBuilder.build(ResultCode.LOGOUT, ResultCode.findMessageByCode(ResultCode.LOGOUT), 1);
    }
}
