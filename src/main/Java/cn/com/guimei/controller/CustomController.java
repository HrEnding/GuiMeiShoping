package cn.com.guimei.controller;

import cn.com.guimei.pojo.Customer;
import cn.com.guimei.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/custom")
public class CustomController {
    @Resource
    private CustomerService customerService;
    @RequestMapping("/union")
    public String doUserUnionPageList(String flag,Customer customer,String pageNumber, HttpServletRequest request){
        if ("true".equals(flag)){
            request.setAttribute("error","操作成功");
        }else if("false".equals(flag)){
            request.setAttribute("error","操作失败");
        }
        Map<String,Object> map  = customerService.userUnionPageList(pageNumber, customer);
        map.put("tip","custom");
        long id = customer.getId()==null?0:customer.getId();
        map.put("servletURL","/custom/union?id="+id+"&cusCode="+customer.getCusCode()+"&cusSex="+customer.getCusSex()+"&pageNumber=");
        request.setAttribute("Page",map);
        return "showCustomer";
    }
    @RequestMapping("/updateById")
    public String updateById(int id,HttpServletRequest request){
        Customer customer = customerService.queryById(id);
        if (customer!=null){
            request.setAttribute("cus",customer);
            return "updateCusInfo";
        }else {
            request.setAttribute("error","数据库出错");
            return "showCustomer";
        }
    }
    @RequestMapping("/customUpdate")
    public String customUpdate(Customer customer,HttpServletRequest request){
        if (customerService.customUpdate(customer)){
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/custom/union?flag=true&pageNumber=1";
        }else {
            request.setAttribute("error","修改失败");
            request.setAttribute("cus",customer);
            return "updateCusInfo";
        }
    }
    @RequestMapping("/customDelete")
    public String customDelete(String id,HttpServletRequest request){
        if (id!=null && id!=""){
            int delId = Integer.parseInt(id);
            if (customerService.customDelete(delId)){
                return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/custom/union?flag=true&pageNumber=1&id=0";
            }
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/custom/union?flag=false&pageNumber=1&id=0";
    }
}
