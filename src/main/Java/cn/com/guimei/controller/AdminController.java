package cn.com.guimei.controller;

import cn.com.guimei.pojo.Superuser;
import cn.com.guimei.pojo.UserLogin;
import cn.com.guimei.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @RequestMapping("/login")
    public String login(UserLogin userLogin, HttpServletRequest request){
        if ("1".equals(userLogin.getUserPost())){
            Superuser superuser = adminService.login(userLogin.getUserLoginName(),userLogin.getUserPassword());
            if (superuser!=null){
                request.getSession().setAttribute("admin",superuser);
                return "index";
            } else {
                request.setAttribute("error","登陆失败");
                return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/Login.jsp";
            }
        }else {
            request.setAttribute("error","暂无功能");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/Login.jsp";
        }
    }
    @RequestMapping("/showInfo")
    public String showInfo(int id,HttpServletRequest request){
        Superuser superuser = adminService.showInfo(id);
        request.getSession().setAttribute("admin",superuser);
        return "adminInfo";
    }
}
