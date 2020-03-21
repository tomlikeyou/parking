import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parking.common.User;
import parking.manager.Application;
import parking.manager.service.IUserService;

import java.util.*;

/**
 * @author huang
 * @date 2020/1/16 14:10
 * @Disc
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void testFind() {
        User user = userService.getUserByUserName("zhangsan");
        System.out.println(user);
    }

    @Test
    public void testFindPages() {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", "kbkh");
        map.put("deleteFlag","0");
        List<User> list = userService.findUsersByMap(map);
        list.stream().forEach(user -> {
            System.out.println(user.toString());
        });
    }

    @Test
    public void testSave() {
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUserName(randomUserName(10));
            user.setPassword("123456");
            user.setPhone("12345678901");
            int save = userService.save(user);
        }
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(12);
        user.setUserName("zhangwei");
        user.setPassword("123456");
        user.setPhone("12345678902");
        user.setRemark("sda");
        System.out.println(userService.modify(user));
    }

    @Test
    public void testDel() {
        System.out.println(userService.delete(12));
    }

    static String randomUserName(int length) {
        StringBuilder sb = new StringBuilder("");
        char[] englishArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char english = englishArray[random.nextInt(englishArray.length)];
            sb.append(english);
        }
        return sb.toString();
    }
}
