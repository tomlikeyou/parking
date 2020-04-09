package parking.manager.service;

import com.github.pagehelper.PageInfo;
import parking.common.Role;

import java.util.List;
import java.util.Map;

/**
 * @author huang
 * @date 2020/1/13 11:29
 * @Disc
 **/
public interface IRoleService {

    List<Role> getRolesByUserId(Integer userId);

    PageInfo<Role> findRolesByMap(Map<String, Object> map);

    int saveRole(Role role);
}
