package parking.manager.web;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import parking.common.AjaxResult;
import parking.common.AjaxResultBuilder;
import parking.common.ResultCode;
import parking.common.User;
import parking.manager.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author: huang
 * Date: created in 2020/1/7 23:14
 * Description:
 * @author 24626
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/user", produces = "application/json")
    public Object getUsers(@RequestParam(value = "userName", required = false) String userName,
                           @RequestParam(value = "deleteFlag", required = false) Character deleteFlag,
                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", userName);
        paramMap.put("deleteFlag", deleteFlag);
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userService.findUsersByMap(paramMap);
        PageInfo<User> userInfo = new PageInfo<>(users);
        return userInfo;

    }

    @GetMapping("/user/{userId}")
    public Object find(@PathVariable("userId") Integer userId) {
        User user = userService.getUserById(userId);
        return AjaxResultBuilder.build(ResultCode.SELECT_SUCCESS, ResultCode.findMessageByCode(ResultCode.SELECT_SUCCESS), user);
    }

    @PostMapping("/user")
    public Object save(@RequestBody User user) {
        int flag = userService.save(user);
        return flag > 0 ? AjaxResultBuilder.build(ResultCode.SAVE_SUCCESS, ResultCode.findMessageByCode(ResultCode.SAVE_SUCCESS), user) : AjaxResultBuilder.build(ResultCode.SAVE_FAIL, ResultCode.findMessageByCode(ResultCode.SAVE_FAIL), null);
    }

    @PostMapping("/file")
    public Object upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return new AjaxResult<>(ResultCode.UPLOAD_FAIL, "file should not be null", null);
        } else {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            //将图片保存到
            String realPath = null;
            try {
                realPath = ResourceUtils.getURL("classpath:").getPath();
                System.out.println(realPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //拿到文件名
            String fileName = file.getOriginalFilename();
            //拿到后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //新的文件名
            fileName = UUID.randomUUID() + suffixName;
            File actualFile = new File(realPath, "static/images/" + fileName);
            if (!actualFile.getParentFile().exists()) {
                actualFile.getParentFile().mkdirs();
            }
            System.out.println(actualFile.getAbsolutePath());
            try {
                file.transferTo(actualFile);
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/file";
                System.out.println(url);
                //更新数据库的图片路径
                user.setUserImage(fileName);
                int flag = userService.modify(user);
                return flag > 0 ? AjaxResultBuilder.build(ResultCode.UPLOAD_SUCCESS, ResultCode.findMessageByCode(ResultCode.UPLOAD_SUCCESS), user) : AjaxResultBuilder.build(ResultCode.UPLOAD_FAIL, ResultCode.findMessageByCode(ResultCode.UPLOAD_FAIL), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @PutMapping("/user")
    public Object modify(@RequestBody User user) {
        int flag = userService.modify(user);
        return flag > 0 ? AjaxResultBuilder.build(ResultCode.EDIT_SUCCESS, ResultCode.findMessageByCode(ResultCode.EDIT_SUCCESS), flag) : AjaxResultBuilder.build(ResultCode.EDIT_FAIL, ResultCode.findMessageByCode(ResultCode.EDIT_FAIL), null);
    }

    @DeleteMapping("/user/{userId}")
    public Object delete(@PathVariable("userId") Integer userId) {
        int flag = userService.delete(userId);
        return flag > 0 ? AjaxResultBuilder.build(ResultCode.DELETE_SUCCESS, ResultCode.findMessageByCode(ResultCode.DELETE_SUCCESS), flag) : AjaxResultBuilder.build(ResultCode.DELETE_FAIL, ResultCode.findMessageByCode(ResultCode.DELETE_FAIL), null);
    }
}
