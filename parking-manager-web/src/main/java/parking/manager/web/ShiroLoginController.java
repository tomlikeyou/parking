package parking.manager.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import parking.common.AjaxResult;
import parking.common.ResultCode;
import parking.common.User;

/**
 * @author huang
 * @date 2020/1/13 14:47
 * @Disc
 **/
@Controller
public class ShiroLoginController {

    @RequestMapping("/login")
    @ResponseBody
    public Object login(@RequestBody User user){
        System.out.println(user.toString());
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
            try{
                subject.login(token);

            }catch (UnknownAccountException uae){
                return  new AjaxResult(ResultCode.FAIL,"账户不存在",null);
            }catch (IncorrectCredentialsException ice){
                return  new AjaxResult(ResultCode.FAIL,"密码不正确",null);
            }catch (AuthenticationException e){
                return  new AjaxResult(ResultCode.FAIL,"未知错误发生",null);
            }
        }
        return  new AjaxResult<>(ResultCode.SUCCESS,"success",(User)subject.getPrincipal());
    }
}
