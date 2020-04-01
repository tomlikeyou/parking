package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.AjaxResult;
import parking.common.AjaxResultBuilder;
import parking.common.Car;
import parking.common.ResultCode;
import parking.manager.service.ICarService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huang
 * @date 2020/1/8 13:16
 * @Disc
 **/
@RestController
public class CarController {

    @Autowired
    private ICarService carService;

    @GetMapping("/cars")
    public Object getCars(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize") Integer pageSize,
                          @RequestParam(value = "carTypeId") Integer carTypeId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("carTypeId", carTypeId);
        return carService.findCarsByMap(map);
    }

    @PostMapping("/car")
    public Object save(@RequestBody Car car) {
        int flag = carService.save(car);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.SAVE_SUCCESS, ResultCode.findMessageByCode(ResultCode.SAVE_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
    }

    @GetMapping("/car/{carId}")
    public Object find(@PathVariable(value = "carId") Integer carId) {
        Car car = carService.findCarById(carId);
        return car != null ? new AjaxResult<>(ResultCode.SELECT_SUCCESS, "SELECT SUCCESS", car) : new AjaxResult<>(ResultCode.SELECT_FAIL, "SELECT ERROR", null);
    }

    @PutMapping(value = "/car")
    public Object modify(@RequestBody Car car) {
        int flag = carService.modify(car);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.EDIT_SUCCESS, ResultCode.findMessageByCode(ResultCode.EDIT_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.EDIT_FAIL, ResultCode.findMessageByCode(ResultCode.EDIT_FAIL), null);

    }

    @DeleteMapping(value = "/car/{carId}")
    public Object delete(@PathVariable(value = "carId") Integer carId) {
        int flag = carService.del(carId);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.DELETE_SUCCESS, ResultCode.findMessageByCode(ResultCode.DELETE_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.DELETE_FAIL, ResultCode.findMessageByCode(ResultCode.DELETE_FAIL), null);

    }
}
