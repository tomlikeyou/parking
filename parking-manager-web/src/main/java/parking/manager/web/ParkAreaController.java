package parking.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import parking.common.ParkArea;
import parking.manager.service.IAreaService;

/**
 * @author huang
 * @date 2020/1/13 15:43
 * @Disc
 **/
@RestController
public class ParkAreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping(value = "/areas")
    public Object getParkArea() {
        return null;
    }

    @PutMapping(value = "/area")
    public Object put(@RequestBody ParkArea parkArea) {
        return null;
    }

}
