package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Role;
import parking.manager.mapper.RoleMapper;
import parking.manager.service.IRoleService;

import java.util.List;

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
    public List<Role> getRolesByUserName(Integer userId) {
        return mapper.getRolesByUserName(userId);
    }
}
