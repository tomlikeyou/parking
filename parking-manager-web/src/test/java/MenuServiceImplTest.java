import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parking.manager.Application;
import parking.manager.service.IMenuService;

/**
 * Author: huang
 * Date: created in 2020/3/21 9:47
 * Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MenuServiceImplTest {

    @Autowired
    private IMenuService menuService;

    @Test
    public void testFindMenus() {
        menuService.findMenus().stream().forEach(menu -> {
            System.out.print(menu.getMenuId() + " " + menu.getMenuName() + " " + menu.getParentId());
            System.out.println(menu.getChildrenList());
        });
    }
}
