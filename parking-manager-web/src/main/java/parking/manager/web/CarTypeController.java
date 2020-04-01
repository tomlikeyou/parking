package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parking.common.AjaxResultBuilder;
import parking.common.CarType;
import parking.common.ResultCode;
import parking.manager.service.ICarTypeService;

import java.util.List;

/**
 * Author: huang
 * Date: created in 2020/3/25 22:17
 * Description:
 *
 * @author 24626
 */
@RestController
public class CarTypeController {

    @Autowired
    private ICarTypeService carTypeService;

    @GetMapping("/carTypes")
    public Object findCarTypes() {
        List<CarType> list = carTypeService.findCarTypes();
        return (list != null && list.size() > 0) ?
                AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), list) :
                AjaxResultBuilder.build(ResultCode.SELECT_FAIL, ResultCode.findMessageByCode(ResultCode.SELECT_FAIL), null);
    }
}
