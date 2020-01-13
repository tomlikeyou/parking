package parking.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huang
 * @date 2020/1/13 14:02
 * @Disc
 **/
@Data
public class Menu implements Serializable {
    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private String url;
    private String icon;
    private Integer orderNum;
    private Character menuType;
    private Character visiable;
    private String createBy;
    private String updateBy;
    private Date updateTime;
    private Date createTime;
    private String remark;
    private String perms;
}
