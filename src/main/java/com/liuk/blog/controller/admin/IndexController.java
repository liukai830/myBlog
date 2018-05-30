package com.liuk.blog.controller.admin;

import com.liuk.blog.model.bo.StatisticsBo;
import com.liuk.blog.model.vo.CommentVo;
import com.liuk.blog.model.vo.ContentVo;
import com.liuk.blog.model.vo.LogVo;
import com.liuk.blog.service.ILogService;
import com.liuk.blog.service.ISiteService;
import com.liuk.blog.service.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 后台管理主页面
 * Created By Liuk On 2018/05/20.
 */
@Controller("adminIndexController")
@RequestMapping("admin")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ISiteService siteService;

    @Autowired
    private ILogService logService;

    @Autowired
    private IUserService userService;




    /**
     * 跳转到后台管理主页
     * @return
     */
    @GetMapping(value = "index")
    public String indexForm(HttpServletRequest request){
        LOGGER.info("Enter admin index method");
        List<CommentVo> comments = siteService.recentComments(5);
        List<ContentVo> contents = siteService.recentContents(5);
        StatisticsBo statistics = siteService.getStatistics();
        // 取最新的20条日志
        List<LogVo> logs = logService.getLogs(1, 5);

        request.setAttribute("comments", comments);
        request.setAttribute("articles", contents);
        request.setAttribute("statistics", statistics);
        request.setAttribute("logs", logs);
        LOGGER.info("Exit admin index method");

        return "admin/index";
    }

}
