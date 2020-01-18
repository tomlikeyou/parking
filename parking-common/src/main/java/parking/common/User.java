package parking.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huang
 * @date 2020/1/8 11:45
 * @Disc
 **/
@Data
public class User implements Serializable {

    private Integer userId;
    private String userName;
    private Integer integral;
    private String password;
    private String phone;
    private Integer status;
    private String remark;
    private Character deleteFlag;
}
