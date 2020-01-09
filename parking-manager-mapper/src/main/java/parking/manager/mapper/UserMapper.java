package parking.manager.mapper;

import parking.common.User;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:10
 * Description:
 */
public interface UserMapper {

    User findUserById(Integer userId);

    int saveUser(User user);

    int update(User user);

    int updateFlag(Integer userId);
}
