package parking.manager.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import parking.common.Menu;
import parking.common.Role;
import parking.common.User;
import parking.manager.service.IMenuService;
import parking.manager.service.IRoleService;
import parking.manager.service.IUserService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author huang
 * @date 2020/1/13 11:06
 * @Disc
 **/
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("==================权限控制设置开始执行=============================");
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Role> roles = roleService.getRolesByUserId(user.getUserId());
        Set<String> roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
        Set<Integer> roleIds = roles.stream().map(Role::getRoleId).collect(Collectors.toSet());
        Set<String> menuPerms = Collections.synchronizedSet(new HashSet<>());
        for (Integer roleId : roleIds) {
            List<Menu> list = menuService.findMenuByRoleId(roleId);
            list.stream().map(Menu::getPerms).forEach(perm -> {
                if (perm != null) {
                    menuPerms.add(perm);
                }
            });
            list.stream().forEach(menu -> {
                menu.getChildrenList().stream().map(Menu::getPerms).forEach(perm -> {
                    menuPerms.add(perm);
                });
            });
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleNames);
        info.setStringPermissions(menuPerms);
        System.out.println(info);
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.getUserByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        } else if (user.getDeleteFlag() == '0') {
            throw new LockedAccountException("用户已锁定");
        }

        ByteSource salt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
        return info;
    }

    public static void main(String[] args) {
        String name = "sAdmin";
        SimpleHash hash = new SimpleHash("md5", "123456", name, 1024);
        System.out.println(hash.toString());
    }
}
