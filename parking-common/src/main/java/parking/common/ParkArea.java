package parking.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huang
 * @date 2020/1/13 15:41
 * @Disc 车位实体
 **/
@Data
public class ParkArea implements Serializable {

    private Integer parkAreaId;
    private String parkAreaName;
    private String community;
    private Integer charges;

}
