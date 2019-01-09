package cn.com.guimei.dao;

import cn.com.guimei.pojo.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerDao {

    int customDelete(@Param("id") int id);

    int custonUpdate(Customer customer);

    Customer queryById(@Param("id") int id);

    int totalWhereRecode(Customer customer);

    List<Customer> userWherePageList(Map map);

}
