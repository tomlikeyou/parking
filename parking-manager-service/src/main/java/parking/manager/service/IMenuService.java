package parking.manager.service;

import parking.common.Menu;

import java.util.List;

/**
 * @author huang
 * @date 2020/1/13 14:10
 * @Disc
 **/
public interface IMenuService {
    List<Menu> findMenusByRoleId(Integer roleId);
}
