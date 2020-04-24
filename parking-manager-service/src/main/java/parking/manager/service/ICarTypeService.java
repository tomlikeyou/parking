package parking.manager.service;

import parking.common.CarType;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/3/23 14:37
 * Description:
 * @author 24626
 */
public interface ICarTypeService {

    List<CarType> findCarsWithMap(Map<String, Object> map);

    /**
     *
     * @return carTypeList
     */
    List<CarType> findCarTypes();

    int save(CarType carType);

    List<CarType> findCarTypeWithCars();

}
