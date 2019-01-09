package cn.com.guimei.service;

import cn.com.guimei.pojo.Customer;
import cn.com.guimei.pojo.Page;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    boolean customUpdate(Customer customer);

    boolean customDelete(int id );

    Customer queryById(int id);

    Map<String,Object> userUnionPageList(String pageNumber,Customer customer);

}
