package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.Car;
import parking.manager.service.ICarService;

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
    public Object getCars() {
        return null;
    }

    @PostMapping("/car")
    public Object save(@RequestBody Car car) {
        int flag = carService.save(car);
        return null;
    }

    @GetMapping("/car/{carId}")
    public Object find(@PathVariable(value = "carId") Integer carId) {
        Car car = carService.findCarById(carId);
        return car;
    }

    @PutMapping(value = "/car")
    public Object modify(@RequestBody Car car) {
        int flag = carService.modify(car);
        return null;
    }

    @DeleteMapping(value = "/car/{carId}")
    public Object delete(@PathVariable(value = "carId") Integer carId) {
        int flag = carService.del(carId);
        return null;
    }
}
