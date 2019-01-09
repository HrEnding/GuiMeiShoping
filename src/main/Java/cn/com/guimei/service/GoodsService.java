package cn.com.guimei.service;

import cn.com.guimei.pojo.Discount;
import cn.com.guimei.pojo.Goods;
import cn.com.guimei.pojo.GoodsExt;
import cn.com.guimei.pojo.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {
    Page<GoodsExt> query(String pageNumber, Goods goods);

    List<Discount>queryDis();

    Goods updateById(int id);

    boolean queryGoodsName(Goods goods);

    int updateGoods(Goods goods, MultipartFile goodsFile, String filePath);

    boolean deleteGoods(int id);

    int addGoods(Goods goods, MultipartFile goodsFile, String filePath);
}
