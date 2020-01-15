package parking.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huang
 * @date 2020/1/13 16:09
 * @Disc 车位
 **/
@Data
public class Stall implements Serializable {
    private Integer stallId;
    private String stallName;
    private Integer stallType;
}
