package parking.manager.mapper;

import parking.common.Order;

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
    public Order findOrderByKey(Integer orderId);
}
