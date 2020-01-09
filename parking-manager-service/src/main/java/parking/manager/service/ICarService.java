package parking.manager.service;

import parking.common.Car;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:17
 * Description:
 */
public interface ICarService {
    Car findCarById(Integer carId);

    int modify(Car car);

    int del(Integer carId);

    int save(Car car);
}
