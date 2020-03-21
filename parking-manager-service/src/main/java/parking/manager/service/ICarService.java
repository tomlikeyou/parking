package parking.manager.service;

import parking.common.Car;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:17
 * Description:
 */
public interface ICarService {

    List<Car> findCars();

    Car findCarById(Integer carId);

    int modify(Car car);

    int del(Integer carId);

    int save(Car car);

    Object findCarsByMap(Map<String, Object> map);
}
