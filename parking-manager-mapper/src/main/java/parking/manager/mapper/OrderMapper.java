package parking.manager.mapper;

import parking.common.UserCar;
import parking.common.User;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/3/27 10:52
 * Description:
 *
 * @author 24626
 */
public interface OrderMapper {
    /**
     * @param orderId
     * @return
     */
    public UserCar findOrderByKey(Integer orderId);

    /**
     * @description 根据参数查询订单
     * @param paramMap
     * @return
     */
    List<User> findOrdersByMap(Map<String, Object> paramMap);
}
