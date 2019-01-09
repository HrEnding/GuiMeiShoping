package cn.com.guimei.dao;

import cn.com.guimei.pojo.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SellerDao {

    int totalWhereRecode(Seller seller);

    List<Seller> showSeller(Map map);

    List<Seller>querySe();

    Seller querryById(@Param("id") int id);

    int sellerUpdate(Seller seller);
}
