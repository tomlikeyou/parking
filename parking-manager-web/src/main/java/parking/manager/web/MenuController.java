package parking.manager.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parking.common.AjaxResultBuilder;
import parking.common.Menu;
import parking.common.ResultCode;
import parking.manager.service.IMenuService;

import java.util.List;

/**
 * Author: huang
 * Date: created in 2020/1/20 22:13
 * Description:
 *
 * @author 24626
 */
@RestController
public class MenuController {


    @Autowired
    private IMenuService menuService;

    @GetMapping("/targetMenus")
    public Object findTargetMenus() {
        List<Menu> menuList = menuService.findTargetMenus();
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), menuList);
    }

    @GetMapping("/menus")
    public Object findMenus() {
        List<Menu> menuList = menuService.findMenus();
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), menuList);
    }

    @GetMapping("/menu")
    public Object findParentMenuById(@RequestParam("menuId") Integer menuId) {
        List<Menu> menuList = menuService.findParentMenuById(menuId);
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), menuList);
    }

    @GetMapping("/menu/{menuId}")
    public Object findMenuById(@PathVariable(value = "menuId") Integer menuId) {
        Menu menu = menuService.findMenuById(menuId);
        return menu != null ?
                AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), menu) :
                AjaxResultBuilder.build(ResultCode.SELECT_FAIL, ResultCode.findMessageByCode(ResultCode.SELECT_FAIL), null);
    }

    @RequiresPermissions(value = "menu:insert")
    @PostMapping("/menu")
    public Object save(@RequestBody Menu menu) {
        int flag = menuService.save(menu);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.SAVE_SUCCESS, ResultCode.findMessageByCode(ResultCode.SAVE_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
    }

    @RequiresPermissions(value = "menu:update")
    @PutMapping("/menu")
    public Object update(@RequestBody Menu menu) {
        int updateFlag = menuService.modifyMenu(menu);
        return updateFlag > 0 ? AjaxResultBuilder.build(ResultCode.EDIT_SUCCESS, ResultCode.findMessageByCode(ResultCode.EDIT_SUCCESS), updateFlag)
                : AjaxResultBuilder.build(ResultCode.EDIT_FAIL, ResultCode.findMessageByCode(ResultCode.EDIT_FAIL), null);
    }

    @RequiresPermissions(value = "menu:delete")
    @DeleteMapping("/menu/{menuId}")
    public Object delete(@PathVariable("menuId") Integer menuId,
                        @RequestParam(value = "menuType")String menuType) {
        int flag = menuService.delete(menuId,menuType);
        return flag > 0 ?
                AjaxResultBuilder.build(ResultCode.DELETE_SUCCESS, ResultCode.findMessageByCode(ResultCode.DELETE_SUCCESS), flag)
                : AjaxResultBuilder.build(ResultCode.DELETE_FAIL, ResultCode.findMessageByCode(ResultCode.DELETE_FAIL), null);
    }
}
