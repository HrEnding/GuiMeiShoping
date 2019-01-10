package cn.com.guimei.controller;

import cn.com.guimei.pojo.BigClass;
import cn.com.guimei.pojo.Page;
import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;
import cn.com.guimei.service.BigClassService;
import cn.com.guimei.service.SmallClassService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/smallClass")
public class SmallClassController {
    @Resource
    private SmallClassService smallClassService;
    @Resource
    private BigClassService bigClassService;
    @RequestMapping("/query")
    public String queryAll(String flag, String pageNumber, Smallclass smallclass, HttpServletRequest request){
        if ("true".equals(flag)){
            request.setAttribute("error","操作成功");
        }else if("false".equals(flag)){
            request.setAttribute("error","操作失败");
        }
        Page<SmallClassExt> page = smallClassService.query(pageNumber,smallclass);
        page.setTip("smallClass");
        request.setAttribute("Page",page);
        Long id = smallclass.getId()!=null?smallclass.getId():0;
        Long smBId = smallclass.getSmBId()!=null?smallclass.getSmBId():0;
        String smallName = smallclass.getSmSName()!=null && smallclass.getSmSName().length()>0?smallclass.getSmSName():"";
        page.setServletURL("/smallClass/query?id="+id+"&smSName="+smallName+"&smBId="+smBId+"&pageNumber=");
        return "showSmallClass";
    }
    @RequestMapping(value = "/queryBName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryBName(){
        List<BigClass>list = bigClassService.queryBCName();
        return JSONArray.toJSONString(list);
    }
    @RequestMapping("/updateById")
    public String updateById(int id,HttpServletRequest request){
        SmallClassExt smallClassExt = smallClassService.updateById(id);
        if (smallClassExt!=null) {
            request.setAttribute("smallClass", smallClassExt);
            return "updateSmallClass";
        }else {
            request.setAttribute("error", "数据库出错");
            return "showSmallClass";
        }
    }
    @RequestMapping(value = "/queryName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryName(String smallClassName){
        List<Smallclass>list = smallClassService.queryName(null);
        for (Smallclass sm: list) {
            if (sm.getSmSName().equals(smallClassName)){
                return JSONArray.toJSONString("exists");
            }
        }
        return JSONArray.toJSONString(null);
    }
    @RequestMapping(value = "/querySmName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String querySmName(String smBId){
        List<Smallclass>list = smallClassService.queryName(smBId);
        return JSONArray.toJSONString(list);
    }
    @RequestMapping("/updateSmallClass")
    public String updateSmallClass(Smallclass smallclass,HttpServletRequest request){
        if (smallClassService.smallClassUpdateById(smallclass)){
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/smallClass/query?flag=true";
        }else {
            request.setAttribute("error","修改失败");
            request.setAttribute("smallClass", smallclass);
            return "updateSmallClass";
        }
    }
    @RequestMapping("/delById")
    public String delById(String smallId,HttpServletRequest request){
        int id = smallId!=null?Integer.parseInt(smallId):0;
        int i = smallClassService.smallClassDelete(id);
        if(i==0){
            request.setAttribute("error","不能删除该小分类！该小分类中有对应的商品信息！");
        }
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/smallClass/query?flag=true";
    }
}
