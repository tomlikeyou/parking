package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Car;
import parking.manager.mapper.CarMapper;
import parking.manager.service.ICarService;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:17
 * Description:
 */
@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public Car findCarById(Integer carId) {
        return carMapper.selectById(carId);
    }

    @Override
    public int modify(Car car) {
        return carMapper.update(car);
    }

    @Override
    public int del(Integer carId) {
        return carMapper.updateFlag(carId);
    }

    @Override
    public int save(Car car) {
        return carMapper.insert(car);
    }
}
