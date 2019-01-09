package cn.com.guimei.controller;

import cn.com.guimei.pojo.Seller;
import cn.com.guimei.service.SellerService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerController {
    @Resource
    private SellerService sellerService;

    @RequestMapping("/union")
    public String doUserUnionPageList(String flag, Seller seller,String pageNumber, HttpServletRequest request){
        if ("true".equals(flag)){
            request.setAttribute("error","操作成功");
        }else if("false".equals(flag)){
            request.setAttribute("error","操作失败");
        }
        Map<String,Object> map  = sellerService.showSeller(pageNumber, seller);
        map.put("tip","seller");
        map.put("servletURL","/seller/union?id="+seller.getSeId()+"&seCode="+seller.getSeUser()+"&seSex="+seller.getSeSex()+"&pageNumber=");
        request.setAttribute("Page",map);
        return "showSeller";
    }
    @RequestMapping("/updateById")
    public String querryById(int id,HttpServletRequest request){
        Seller seller = sellerService.querryById(id);
        if (seller!=null){
            request.setAttribute("seller",seller);
            return "updateSeller";
        }else {
            request.setAttribute("error","数据库出错");
            return "showSeller";
        }
    }
    @RequestMapping("/sellerUpdate")
    public String sellerUpdate(Seller seller,HttpServletRequest request){
        if (sellerService.sellerUpdate(seller)){
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/seller/union?flag=true&pageNumber=1";
        }else {
            request.setAttribute("error","修改失败");
            request.setAttribute("seller",seller);
            return "updateSeller";
        }
    }
    @RequestMapping(value = "/querySe",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String querySe(){
        List<Seller>list = sellerService.querySe();
        return JSONArray.toJSONString(list);
    }
}
