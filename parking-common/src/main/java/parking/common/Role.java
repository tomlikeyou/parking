package parking.common;

import lombok.Data;

import java.util.Date;

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
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String remark;
}
