package parking.manager.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.User;
import parking.manager.mapper.UserMapper;
import parking.manager.service.IUserService;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:16
 * Description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User getUserById(Integer userId) {
        return mapper.findUserById(userId);
    }

    @Override
    public int save(User user) {
        //加密算法
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashIterations = 1024;
        String pwd = user.getPassword();
        //盐值加密
        ByteSource salt = ByteSource.Util.bytes(user.getUserName());
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, pwd, salt, hashIterations);
        user.setPassword(simpleHash.toString());
        return mapper.saveUser(user);
    }

    @Override
    public int modify(User user) {
        //加密算法
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashIterations = 1024;
        SimpleHash hash = new SimpleHash(hashAlgorithmName, user.getPassword(), ByteSource.Util.bytes(user.getUserName()), hashIterations);
        user.setPassword(hash.toString());
        return mapper.updateInfo(user);
    }

    @Override
    public int delete(Integer userId) {
        return mapper.updateFlag(userId);
    }

    @Override
    public User getUserByUserName(String userName) {
        return mapper.findUserByUserName(userName);
    }

    @Override
    public List<User> findUsersByMap(Map<String, Object> paramMap) {
        return mapper.findUsersByMap(paramMap);
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashIterations = 1024;
        String pwd = "123456";
        ByteSource salt = ByteSource.Util.bytes("lisi");
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, pwd, salt, hashIterations);
        System.out.println(simpleHash.toString());
    }
}
