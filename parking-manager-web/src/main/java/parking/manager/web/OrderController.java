package parking.manager.web;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.*;
import parking.manager.service.IOrderService;

import java.util.Date;
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

    @RequiresPermissions(value = "order:insert")
    @PostMapping(value = "/order")
    public Object addOrder(@RequestBody Map<String, Object> map) {
        int flag = orderService.saveOrder(map);
        return flag > 0 ? AjaxResultBuilder.build(ResultCode.SAVE_SUCCESS, ResultCode.findMessageByCode(ResultCode.SAVE_SUCCESS), 1)
                : AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
    }

    @RequiresPermissions(value = "order:update")
    @PutMapping("/order")
    public Object modify(@RequestBody HashMap<String, Object> map) {
        System.out.println(map);
        int flag = orderService.modify(map);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.EDIT_SUCCESS, ResultCode.findMessageByCode(ResultCode.EDIT_SUCCESS), 1)
                : AjaxResultBuilder.build(ResultCode.EDIT_FAIL, ResultCode.findMessageByCode(ResultCode.EDIT_FAIL), 0);
    }

    @RequiresPermissions(value = "order:delete")
    @DeleteMapping("/order/{userId}/{carId}")
    public Object delOrder(@PathVariable(value = "userId") Integer userId,
                           @PathVariable(value = "carId") Integer carId) {
        int flag = orderService.deleteOrder(userId, carId);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.DELETE_SUCCESS, ResultCode.findMessageByCode(ResultCode.DELETE_SUCCESS), 1)
                : AjaxResultBuilder.build(ResultCode.DELETE_SUCCESS, ResultCode.findMessageByCode(ResultCode.DELETE_FAIL), 0);
    }
}
