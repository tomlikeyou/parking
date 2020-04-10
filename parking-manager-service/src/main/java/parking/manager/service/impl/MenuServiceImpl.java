package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Menu;
import parking.common.RoleMenuLink;
import parking.manager.mapper.MenuMapper;
import parking.manager.service.IMenuService;

import java.util.*;

/**
 * @author huang
 * @date 2020/1/13 14:11
 * @Disc
 **/
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenusByRoleId(Integer roleId) {
        List<Menu> menus = menuMapper.findMenusByRoleId(roleId);
        List<Menu> menuList = new ArrayList<>();
        Map<Integer, Menu> map = new HashMap<>();
        menus.stream().forEach(menu -> {
            map.put(menu.getMenuId(), menu);
        });
        menus.stream().forEach(menu -> {
            if (menu.getParentId() == 0) {
                menuList.add(menu);
            } else {
                Menu parent = map.get(menu.getParentId());
                parent.getChildrenList().add(menu);
            }
        });
        return menuList;
    }

    @Override
    public Menu findMenuById(Integer menuId) {
        return menuMapper.findMenuById(menuId);
    }

    @Override
    public int delete(Integer menuId, String menuType) {
        int flag = 0;
        //删除按钮
        if ("0".equals(menuType)) {
            flag = menuMapper.delete(menuId);
            if (flag <= 0) {
                return flag;
            }
        } else {
            //要删除的是菜单
            //先判断要删除菜单是否有子菜单
            List<Menu> menus = menuMapper.findMenus();
            List<Menu> menuList = buildTreeList(menus, menuId);
            if (menuList != null && menuList.size() > 0) {
                //有子菜单，先将子菜单的parentId设置为删除菜单的parentId,然后再删除
                Menu menu = menuMapper.findMenuById(menuId);
                menuList.stream().forEach((child) -> {
                    child.setParentId(menu.getParentId());
                    menuMapper.modifyMenu(child);
                });
                flag = menuMapper.delete(menuId);
                if (flag <= 0) {
                    return flag;
                }
            } else {
                //没有子菜单
                flag = menuMapper.delete(menuId);
                if (flag <= 0) {
                    return flag;
                }
            }
        }
        //查询中间表是否有对应的角色权限关系，有则再删除
        List<RoleMenuLink> list = menuMapper.findLinkByMenuId(menuId);
        if (list != null) {
            list.stream().forEach((link) -> {
                menuMapper.deleteRm(link);
            });
            return 1;
        }
        return 0;
    }

    @Override
    public int modifyMenu(Menu menu) {
        return menuMapper.modifyMenu(menu);
    }

    @Override
    public List<Menu> findMenus() {
        //拿到原始菜单列表
        List<Menu> menus = menuMapper.findMenus();
        List<Menu> menuList = buildTreeList(menus, 0);
        return menuList;
    }

    /**
     * @param menuList
     * @param parentId
     * @return 返回树型菜单
     */
    private List<Menu> buildTreeList(List<Menu> menuList, Integer parentId) {
        List<Menu> treeList = new ArrayList<>();
        menuList.stream().forEach(menu -> {
            if (menu.getParentId().equals(parentId)) {
                menu.setChildrenList(buildTreeList(menuList, menu.getMenuId()));
                treeList.add(menu);
            }
        });
        return treeList;
    }

    @Override
    public int save(Menu menu) {
        return menuMapper.save(menu);
    }

    @Override
    public List<Menu> findParentMenuById(Integer menuId) {
        return menuMapper.findParentMenuById(menuId);
    }

    @Override
    public List<Menu> findTargetMenus() {
        return menuMapper.findTargetMenus();
    }
}
