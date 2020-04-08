package parking.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author huang
 * @date 2020/1/8 11:45
 * @Disc
 **/
@Data
public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String password;
    private Character sex;
    private String phone;
    private Integer status;
    private String remark;
    private Character deleteFlag;
    private String licenseNumber;
    private String userImage;
    private Set<Role> roleSet;
    private List<Menu> menuList;
    private Set<String> permList;
    private String Authorization;
    private List<UserCar> userCarList;

    public void setAuthorization(Serializable id) {
        this.Authorization = (String) id;
    }
}
