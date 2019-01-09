package cn.com.guimei.service;

import cn.com.guimei.pojo.Seller;

import java.util.List;
import java.util.Map;

public interface SellerService {
    Map<String,Object> showSeller(String pageNumber, Seller seller);

    Seller querryById(int id);

    List<Seller>querySe();

    boolean sellerUpdate(Seller seller);

}
