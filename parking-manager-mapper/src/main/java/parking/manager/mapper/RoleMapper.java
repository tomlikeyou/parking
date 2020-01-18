package parking.manager.mapper;

import parking.common.Role;

import java.util.List;

/**
 * @author huang
 * @date 2020/1/13 11:38
 * @Disc
 **/
public interface RoleMapper {

    List<Role> getRolesByUserName(Integer userId);
}
