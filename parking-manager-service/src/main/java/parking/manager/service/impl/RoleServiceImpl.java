package parking.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Role;
import parking.manager.mapper.RoleMapper;
import parking.manager.service.IRoleService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author huang
 * @date 2020/1/13 11:30
 * @Disc
 **/
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public PageInfo<Role> findRolesByMap(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Role> roles = roleMapper.findRolesByMap(map);
        PageInfo<Role> roleInfo = new PageInfo<>(roles);
        return roleInfo;
    }

    @Override
    public int saveRole(Role role) {
        int flag = roleMapper.saveRole(role);
        int rmFlag = 0;
        if (role.getMenuIds() != null) {
            rmFlag = saveRm(role);
        }
        return (flag & rmFlag);
    }

    @Override
    public int modifyFlag(Integer roleId) {
        return roleMapper.modifyFlag(roleId);
    }

    @Override
    public int modifyRole(Role role) {
        return roleMapper.modifyRole(role);
    }

    private int saveRm(Role role) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", role.getRoleId());
        Arrays.asList(role.getMenuIds()).stream().forEach(
                (menuId) -> {
                    map.put("menuId", menuId);
                    roleMapper.saveRm(map);
                    map.remove("menuId");
                }
        );
        return 1;
    }
}
