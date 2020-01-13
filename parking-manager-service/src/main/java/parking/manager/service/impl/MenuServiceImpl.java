package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Menu;
import parking.manager.mapper.MenuMapper;
import parking.manager.service.IMenuService;

import java.util.List;

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
        return menuMapper.findMenusByRoleId(roleId);
    }
}
