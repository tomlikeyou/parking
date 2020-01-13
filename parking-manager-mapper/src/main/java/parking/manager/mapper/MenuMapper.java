package parking.manager.mapper;

import parking.common.Menu;

import java.util.List;

/**
 * @author huang
 * @date 2020/1/13 14:11
 * @Disc
 **/
public interface MenuMapper {

    List<Menu> findMenusByRoleId(Integer roleId);
}
