package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Order;
import parking.manager.mapper.OrderMapper;
import parking.manager.service.IOrderService;

/**
 * Author: huang
 * Date: created in 2020/3/27 10:51
 * Description:
 *
 * @author 24626
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order findOrderByKey(Integer orderId) {
        return orderMapper.findOrderByKey(orderId);
    }
}
