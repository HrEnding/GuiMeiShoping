package cn.com.guimei.controller;

import cn.com.guimei.pojo.Discount;
import cn.com.guimei.pojo.Goods;
import cn.com.guimei.pojo.GoodsExt;
import cn.com.guimei.pojo.Page;
import cn.com.guimei.service.GoodsService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @RequestMapping("/union")
    public String queryAll(String flag, String pageNumber, Goods goods, HttpServletRequest request){
        if ("true".equals(flag)){
            request.setAttribute("error","操作成功");
        }else if("false".equals(flag)){
            request.setAttribute("error","操作失败");
        }
        Page<GoodsExt> page = goodsService.query(pageNumber,goods);
        page.setTip("goods");
        request.setAttribute("Page",page);
        Long id = goods.getId()!=null?goods.getId():0;
        Long gSeId = goods.getgSeId()!=null?goods.getgSeId():0;
        Long gSmId = goods.getgSmId()!=null?goods.getgSmId():0;
        page.setServletURL("/goods/union?id="+id+"&gSeId="+gSeId+"&gSmId="+gSmId+"&pageNumber=");
        return "showGoods";
    }
    @RequestMapping(value = "/queryDis",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryDis(){
        List<Discount>list = goodsService.queryDis();
        return JSONArray.toJSONString(list);
    }
    @RequestMapping("/updateById")
    public String updateById(int id,HttpServletRequest request){
        Goods goods = goodsService.updateById(id);
        if (goods!=null) {
            request.setAttribute("goods", goods);
            return "updateGoods";
        }else {
            request.setAttribute("error", "数据库出错");
            return "showGoods";
        }
    }
    @RequestMapping(value = "/queryGoodsName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryGoodsName(Goods goods){
        System.out.println(goods.toString());
        if (goodsService.queryGoodsName(goods)){
            return JSONArray.toJSONString("exists");
        }
        return JSONArray.toJSONString(null);
    }
    @RequestMapping("/updateGoods")
    public String updateGoods(Goods goods,MultipartFile goodsImages,HttpServletRequest request){
        String filePath = request.getRealPath("/static/images/goodsImage");
        int i = goodsService.updateGoods(goods,goodsImages,filePath);
        if(i==1){
            request.setAttribute("error","图像文件路径不存在！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }else if(i==2){
            request.setAttribute("error","添加商品失败！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }else if(i==3){
            request.setAttribute("error","上传文件不能操作5MB！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }else if(i==4){
            request.setAttribute("error","文件类型非要求的图像格式！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/updateGoods.jsp";
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/goods/union?flag=true";
    }
    @RequestMapping("/delById")
    public String deleteGoods(String goodsId){
        int id = goodsId!=null?Integer.parseInt(goodsId):0;
        if(goodsService.deleteGoods(id)){
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/goods/union?flag=true&id=0";
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/goods/union?flag=false&id=0";
    }
    @RequestMapping("/addGoodsPage")
    public String addGoodsPage(){
        return "addGoods";
    }
    @RequestMapping("/addGoods")
    public String addGoods(Goods goods, MultipartFile goodsImages, HttpServletRequest request) {
        String filePath = request.getRealPath("/static/images/goodsImage");
        int i = goodsService.addGoods(goods,goodsImages,filePath);
        if(i==1){
            request.setAttribute("error","图像文件路径不存在！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }else if(i==2){
            request.setAttribute("error","添加商品失败！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }else if(i==3){
            request.setAttribute("error","上传文件不能操作5MB！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }else if(i==4){
            request.setAttribute("error","文件类型非要求的图像格式！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/page/addGoods.jsp";
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/goods/union?flag=true";
    }
}
