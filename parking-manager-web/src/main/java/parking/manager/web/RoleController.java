package parking.manager.web;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.AjaxResultBuilder;
import parking.common.ResultCode;
import parking.common.Role;
import parking.manager.service.IRoleService;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: huang
 * Date: created in 2020-04-08 17:21
 * Description:
 *
 * @author 24626
 */
@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/roles")
    public Object findRolesByMap(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "roleName", required = false) String roleName) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("roleName", roleName);
        PageInfo<Role> rolePageInfo = roleService.findRolesByMap(map);
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), rolePageInfo);
    }

    @RequiresPermissions(value = "role:insert")
    @PostMapping("/role")
    public Object saveRole(@RequestBody Role role) {
        if (role == null) {
            return AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
        } else {
            int flag = roleService.saveRole(role);
            return flag > 0 ? AjaxResultBuilder.build(ResultCode.SAVE_SUCCESS, ResultCode.findMessageByCode(ResultCode.SAVE_SUCCESS), flag)
                    : AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
        }
    }


    @RequiresPermissions(value = "role:delete")
    @PutMapping("/role/{roleId}")
    public Object modifyFlag(@PathVariable(value = "roleId") Integer roleId) {

        int flag = roleService.modifyFlag(roleId);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.EDIT_SUCCESS, ResultCode.findMessageByCode(ResultCode.EDIT_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.EDIT_FAIL, ResultCode.findMessageByCode(ResultCode.EDIT_FAIL), null);
    }

    @RequiresPermissions(value = {"role:update"})
    @PutMapping("/role")
    public Object modifyRole(@RequestBody Role role) {
        int flag = roleService.modifyRole(role);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.EDIT_SUCCESS, ResultCode.findMessageByCode(ResultCode.EDIT_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.EDIT_FAIL, ResultCode.findMessageByCode(ResultCode.EDIT_FAIL), null);

    }
}
