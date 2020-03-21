package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Menu;
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
    public int delete(Integer menuId) {
        return menuMapper.delete(menuId);
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
}
