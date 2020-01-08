package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import parking.common.User;
import parking.manager.mapper.UserMapper;
import parking.manager.service.IUserService;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:16
 * Description:
 */
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User getUserById(Integer userId) {
        return mapper.findUserById(userId);
    }
}
