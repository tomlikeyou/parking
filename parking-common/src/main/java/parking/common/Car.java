package parking.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huang
 * @date 2020/1/9 10:23
 * @Disc
 **/
@Data
public class Car implements Serializable {

    private Integer carId;
    private String carType;
    private Integer flag;
    private String remark;
}
