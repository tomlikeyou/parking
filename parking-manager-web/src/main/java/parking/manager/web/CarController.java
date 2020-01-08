package parking.manager.web;

import org.springframework.web.bind.annotation.*;

/**
 * @author huang
 * @date 2020/1/8 13:16
 * @Disc
 **/
@RestController
public class CarController {

    @GetMapping("/cars")
    public Object getCars() {
        return null;
    }

    @PostMapping("/car")
    public Object save() {
        return null;
    }

    @GetMapping("/car}/carId")
    public Object find() {
        return null;
    }

    @PutMapping(value = "/car")
    public Object modify() {
        return null;
    }

    @DeleteMapping(value = "/car")
    public Object delete() {
        return null;
    }
}
