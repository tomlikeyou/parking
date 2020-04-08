package parking.manager.mapper;

import parking.common.CarType;

import java.util.List;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020/3/23 13:33
 * Description:
 *
 * @author 24626
 */
public interface CarTypeMapper {

    /**
     * @return carTypeList with map
     */
    public List<CarType> findCarsWithMap(Map<String, Object> map);

    /**
     *
     * @return carTypeList
     */
    public List<CarType> findCarTypes();

    int insert(CarType carType);
}
