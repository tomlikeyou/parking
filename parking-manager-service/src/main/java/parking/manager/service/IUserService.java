package parking.manager.service;

import parking.common.User;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:16
 * Description:
 */
public interface IUserService {


    public User getUserById(Integer userId);

    int save(User user);

    int modify(User user);

    int modifyImage(Map<String,Object> map);

    int delete(Integer userId);

    User getUserByUserName(String userName);

    List<User> findUsersByMap(Map<String, Object> paramMap);

    List<User> findUsers();
    
}
