package parking.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date purchaseDate;
    private Integer carCount;
    private Integer dayPrice;
    private Integer monthPrice;
    private String carArea;
    private List<UserCar> userCarList;
}
