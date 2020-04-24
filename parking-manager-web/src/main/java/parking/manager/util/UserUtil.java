package parking.manager.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Author: huang
 * Date: created in 2020-04-20 14:37
 * Description: 用户权限判断
 * @author 24626
 */
public class UserUtil {
    public static boolean hasPermission(String permissionName) {
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(permissionName);
    }
}
