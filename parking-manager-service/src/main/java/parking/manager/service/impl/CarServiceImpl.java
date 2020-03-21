package parking.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.Car;
import parking.manager.mapper.CarMapper;
import parking.manager.service.ICarService;

import java.io.File;
import java.util.List;
import java.util.Map;

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
    public List<Car> findCars() {
        return carMapper.findCars();
    }

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

    @Override
    public Object findCarsByMap(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Car> cars = carMapper.findCarsByMap(map);
        PageInfo<Car> carPageInfo = new PageInfo<>(cars);
        return carPageInfo;
    }
}
