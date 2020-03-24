package parking.common;

import lombok.Data;

import java.util.List;

/**
 * Author: huang
 * Date: created in 2020/3/23 13:31
 * Description:
 * @author 24626
 */
@Data
public class CarType {

    private Integer typeId;
    private String typeName;
    private Integer inventory;
    private List<Car> carList;
}
