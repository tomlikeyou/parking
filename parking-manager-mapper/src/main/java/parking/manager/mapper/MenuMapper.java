package parking.manager.mapper;

import parking.common.Menu;
import parking.common.RoleMenuLink;

import java.util.List;
import java.util.Set;

/**
 * @author huang
 * @date 2020/1/13 14:11
 * @Disc
 **/
public interface MenuMapper {

    List<Menu> findMenusByRoleId(Integer roleId);

    Menu findMenuById(Integer menuId);

    int delete(Integer menuId);

    int modifyMenu(Menu menu);

    /**
     * @return menus
     */
    List<Menu> findMenus();

    int save(Menu menu);

    List<Menu> findParentMenuById(Integer menuId);

    List<Menu> findTargetMenus();

    List<RoleMenuLink> findLinkByMenuId(Integer menuId);

    int deleteRm(RoleMenuLink link);

    List<Menu> findPermsByRoleId(Integer roleId);
}
