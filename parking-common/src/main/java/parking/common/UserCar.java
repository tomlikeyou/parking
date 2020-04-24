package parking.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: huang
 * Date: created in 2020/3/27 10:50
 * Description:
 *
 * @author 24626
 */
@Data
public class UserCar implements Serializable {

    private User user;
    private Car car;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaseStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaseEndTime;
    private Character leaseStatus;
    private Character leaseType;
    private Integer rent;
    /**
     * 删除标志
     */
    private Character deleteFlag;
}
