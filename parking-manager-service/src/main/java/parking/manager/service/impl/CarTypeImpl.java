package parking.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parking.common.CarType;
import parking.manager.mapper.CarTypeMapper;
import parking.manager.service.ICarTypeService;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/3/23 14:37
 * Description:
 * @author 24626
 */
@Service
public class CarTypeImpl implements ICarTypeService {

    @Autowired
    private CarTypeMapper carTypeMapper;
    @Override
    public List<CarType> findCarsWithMap(Map<String, Object> map) {
     return  carTypeMapper.findCarsWithMap(map);
    }

    @Override
    public List<CarType> findCarTypes() {
        return carTypeMapper.findCarTypes();
    }

    @Override
    public int save(CarType carType) {
        return carTypeMapper.insert(carType);
    }

    @Override
    public List<CarType> findCarTypeWithCars() {
        return carTypeMapper.findCarTypeWithCars();
    }
}
