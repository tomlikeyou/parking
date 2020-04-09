package parking.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Role;
import parking.manager.mapper.RoleMapper;
import parking.manager.service.IRoleService;

import java.util.Arrays;
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
    private RoleMapper mapper;

    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return mapper.getRolesByUserId(userId);
    }

    @Override
    public PageInfo<Role> findRolesByMap(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Role> roles = mapper.findRolesByMap(map);
        PageInfo<Role> roleInfo = new PageInfo<>(roles);
        return roleInfo;
    }

    @Override
    public int saveRole(Role role) {
        return 1;
    }
}
