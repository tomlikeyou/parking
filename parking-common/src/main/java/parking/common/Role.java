package parking.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer[] menuIds;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private List<Menu> menuList;
}
