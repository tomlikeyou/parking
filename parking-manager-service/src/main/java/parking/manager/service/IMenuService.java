package parking.manager.service;

import parking.common.Menu;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author huang
 * @date 2020/1/13 14:10
 * @Disc
 **/
public interface IMenuService {
    /**
     * @param roleId 菜单id
     * @return 菜单列表
     */
    List<Menu> findMenusByRoleId(Integer roleId);

    /**
     * @param menuId 菜单id
     * @return 菜单
     */
    Menu findMenuById(Integer menuId);

    /**
     *
     * @param menuId
     * @param menuType
     * @return
     */
    int delete(Integer menuId,String menuType);

    int modifyMenu(Menu menu);

    /**
     * @return menusList
     */
    List<Menu> findMenus();


    /**
     * @param menu 菜单
     * @return 更新与否标志
     */
    int save(Menu menu);

    List<Menu> findParentMenuById(Integer menuId);

    List<Menu> findTargetMenus();

    Set<String> findPermsByRoleId(Integer roleId);

    Set<String> findMenuNamesByRoleId(Integer roleId);

    /**
     * @description shiro授权 根据角色id获取权限
     * @param roleId
     * @return
     */
    List<Menu> findMenuByRoleId(Integer roleId);
}
