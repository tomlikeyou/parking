import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parking.common.User;
import parking.manager.Application;
import parking.manager.service.IUserService;

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
    public void testSave() {
        User user = new User();
        user.setUserName("zhangwei");
        user.setPassword("123456");
        user.setPhone("12345678901");
        int save = userService.save(user);
        System.out.println(save);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(102);
        user.setUserName("zhangwei");
        user.setPassword("123456");
        user.setIntegral(100);
        user.setPhone("12345678902");
        user.setRemark("sda");
        System.out.println(userService.modify(user));
    }

    @Test
    public void testDel() {
        System.out.println(userService.delete(102));
    }
}
