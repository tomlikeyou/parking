package parking.manager.service;

import parking.common.Order;

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
    Order findOrderByKey(Integer orderId);
}
