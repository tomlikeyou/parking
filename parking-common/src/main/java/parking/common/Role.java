package parking.common;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author huang
 * @date 2020/1/13 11:30
 * @Disc
 **/
@Data
public class Role {
    private Integer roleId;
    private String roleName;
    private Integer status;
    private Integer deleteFlag;
    private String remark;
    private List<Menu> menuList;
}
