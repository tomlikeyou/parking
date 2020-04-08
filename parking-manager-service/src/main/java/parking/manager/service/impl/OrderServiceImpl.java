package parking.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.UserCar;
import parking.common.User;
import parking.manager.mapper.OrderMapper;
import parking.manager.service.IOrderService;

import java.util.List;
import java.util.Map;

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
    public UserCar findOrderByKey(Integer orderId) {
        return orderMapper.findOrderByKey(orderId);
    }

    @Override
    public PageInfo<User> findOrdersByMap(Map<String, Object> paramMap) {
        PageHelper.startPage((Integer) paramMap.get("pageNum"), (Integer) paramMap.get("pageSize"));
        List<User> userList = orderMapper.findOrdersByMap(paramMap);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userList != null ? userPageInfo : null;
    }
}
