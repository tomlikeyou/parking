package parking.manager.service;

import com.github.pagehelper.PageInfo;
import parking.common.UserCar;
import parking.common.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/3/27 10:51
 * Description:
 *
 * @author 24626
 */
public interface IOrderService {
    /**
     * @param orderId
     * @return Order
     */
    UserCar findOrderByKey(Integer orderId);

    PageInfo<User> findOrdersByMap(Map<String, Object> paramMap);

    int modify(HashMap<String, Object> map);

    int deleteOrder(Integer userId, Integer carId);

    int saveOrder(Map<String, Object> map);
}
