package com.liuk.blog.controller.admin;

import com.liuk.blog.constant.WebConst;
import com.liuk.blog.exception.TipException;
import com.liuk.blog.model.bo.RestResponseBo;
import com.liuk.blog.model.vo.UserVo;
import com.liuk.blog.service.ILogService;
import com.liuk.blog.service.IUserService;
import com.liuk.blog.utils.TaleUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户后台登录/登出
 * Created By Liuk On 2018/05/20.
 */
@Controller
@RequestMapping("admin")
public class AuthController extends BaseController{

    @Autowired
    private IUserService userService;
    @Autowired
    private ILogService logService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @GetMapping(value = "login")
    public String loginForm() {
        System.out.println("go to login.html");
        return "admin/login";
    }

    @PostMapping(value = "doLogin")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam(required = false) String remeber_me,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        Integer error_count = cache.get("login_error_count");
        try {
            UserVo user = userService.login(username, password);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            //记住我
            /*if (StringUtils.isNotBlank(remeber_me)) {
                TaleUtils.setCookie(response, user.getUid());
            }*/
            //logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return RestResponseBo.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg, e);
            }
            return RestResponseBo.fail(msg);
        }
        return RestResponseBo.ok();
    }
}
