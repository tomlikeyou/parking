package parking.manager.service;

import parking.common.Role;

import java.util.List;

/**
 * @author huang
 * @date 2020/1/13 11:29
 * @Disc
 **/
public interface IRoleService {

    List<Role> getRolesByUserName(String userName);
}
