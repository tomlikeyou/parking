import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parking.common.User;
import parking.manager.Application;
import parking.manager.service.IOrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020-04-06 20:31
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void testOrders() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum",1);
        map.put("pageSize",10);
        PageInfo<User> ordersByMap = orderService.findOrdersByMap(map);
        System.out.println(ordersByMap);
    }
}
