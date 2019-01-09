package cn.com.guimei.service.impl;

import cn.com.guimei.dao.SellerDao;
import cn.com.guimei.pojo.Seller;
import cn.com.guimei.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    private SellerDao sellerDao;

    public Map<String, Object> showSeller(String num, Seller seller) {
        Map<String,Object> map = new HashMap<String, Object>();
        int pageSize = 3;
        //默认首次访问首页
        int pageNumber = 1;
        if(num!=null && num.length()>0){
            pageNumber = Integer.parseInt(num);
        }
        int pageRecode = sellerDao.totalWhereRecode(seller);
        //计算共有多少页
        int totalPage = pageRecode%pageSize==0?pageRecode/pageSize:pageRecode/pageSize+1;
        //求当前页的内容
        int pageIndex = (pageNumber-1)*pageSize;
        Map<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("pageIndex",pageIndex);
        hashMap.put("pageSize",pageSize);
        hashMap.put("seller",seller);
        List<Seller> userList = sellerDao.showSeller(hashMap);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("pageData",userList);
        return map;
    }

    public Seller querryById(int id) {
        return sellerDao.querryById(id);
    }

    public List<Seller> querySe() {
        return sellerDao.querySe();
    }

    public boolean sellerUpdate(Seller seller) {
        if (sellerDao.sellerUpdate(seller)>0){
            return true;
        }
        return false;
    }

}
