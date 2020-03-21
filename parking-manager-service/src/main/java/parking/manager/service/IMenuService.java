package parking.manager.service;

import parking.common.Menu;

import java.util.List;

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
     * @param menuId 菜单id
     * @return 删除与否标志
     */
    int delete(Integer menuId);

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
}
