package parking.manager.mapper;

import parking.common.Car;

/**
 * @author huang
 * @date 2020/1/9 10:22
 * @Disc
 **/
public interface CarMapper {


    Car selectById(Integer carId);

    int update(Car car);

    int updateFlag(Integer carId);

    int insert(Car car);
}
