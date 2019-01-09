package cn.com.guimei.service.impl;


import cn.com.guimei.dao.CustomerDao;
import cn.com.guimei.pojo.Customer;
import cn.com.guimei.service.CustomerService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;

    public boolean customUpdate(Customer customer) {
        if (customerDao.custonUpdate(customer)>0){
            return true;
        }
        return false;
    }

    public boolean customDelete(int id) {
        if (customerDao.customDelete(id)>0){
            return true;
        }
        return false;
    }

    public Customer queryById(int id) {
        return customerDao.queryById(id);
    }

    public Map<String, Object> userUnionPageList(String num, Customer customer) {
        Map<String,Object> map = new HashMap<String, Object>();
        int pageSize = 3;
        //默认首次访问首页
        int pageNumber = 1;
        if(num!=null && num.length()>0){
            pageNumber = Integer.parseInt(num);
        }
        int pageRecode = customerDao.totalWhereRecode(customer);
        //计算共有多少页
        int totalPage = pageRecode%pageSize==0?pageRecode/pageSize:pageRecode/pageSize+1;
        //求当前页的内容
        int pageIndex = (pageNumber-1)*pageSize;
        Map<String,Object> hashMap = new HashMap<String, Object>();
        hashMap.put("pageIndex",pageIndex);
        hashMap.put("pageSize",pageSize);
        hashMap.put("user",customer);
        List<Customer> userList = customerDao.userWherePageList(hashMap);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("pageData",userList);
        return map;
    }
}
