package parking.manager.web;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.AjaxResultBuilder;
import parking.common.UserCar;
import parking.common.ResultCode;
import parking.common.User;
import parking.manager.service.IOrderService;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/3/27 10:46
 * Description:订单管理
 *
 * @author 24626
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @GetMapping("/orders")
    public Object findOrders(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("pageNum", pageNum);
        paramMap.put("pageSize", pageSize);
        PageInfo<User> data = orderService.findOrdersByMap(paramMap);
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), data);
    }


    @GetMapping("/order/{orderId}")
    public Object findOrderByKey(@PathVariable(value = "orderId") Integer orderId) {
        UserCar order = orderService.findOrderByKey(orderId);
        return order != null ?
                AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), order)
                : AjaxResultBuilder.build(ResultCode.SELECT_FAIL, ResultCode.findMessageByCode(ResultCode.SELECT_FAIL), null);
    }

    @PostMapping(value = "/order")
    public Object addOrder(@RequestBody UserCar order) {
        return null;
    }

    @PutMapping("/order/{orderId}")
    public Object modify(@PathVariable(value = "orderId") Integer orderId) {
        return null;
    }

    @DeleteMapping("/order/{orderId}")
    public Object delOrder(@PathVariable(value = "orderId") Integer orderId) {
        return null;
    }
}
