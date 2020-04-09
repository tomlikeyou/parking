package parking.manager.mapper;

import parking.common.Role;

import java.util.List;
import java.util.Map;

/**
 * @author huang
 * @date 2020/1/13 11:38
 * @Disc
 **/
public interface RoleMapper {

    List<Role> getRolesByUserId(Integer userId);

    List<Role> findRolesByMap(Map<String, Object> map);

    int saveRole(Role role);
}
