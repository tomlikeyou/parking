package parking.manager.mapper;

import parking.common.User;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:10
 * Description:
 */
public interface UserMapper {

    User findUserByUserName(String userName);

    User findUserById(Integer userId);

    int saveUser(User user);

    int update(User user);

    int updateFlag(Integer userId);

    List<User> findUsersByMap(Map<String, Object> paramMap);

    /**
     *
     * @param user
     * @return update flag
     */
    int updateInfo(User user);
}
