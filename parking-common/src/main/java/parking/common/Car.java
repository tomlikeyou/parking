package parking.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huang
 * @date 2020/1/9 10:23
 * @Disc
 **/
@Data
public class Car implements Serializable {
    private Integer carId;
    private String carName;
    private String carNumber;
    private Integer carTypeId;
    private String carImage;
    private String carColor;
    private String carRemark;
    private Character taxStatus;
    private Date purchaseDate;
    private Integer carCount;
    private Integer dayPrice;
    private Integer monthPrice;
    private String carArea;
}
