package cn.com.guimei.dao;

import cn.com.guimei.pojo.Goods;
import cn.com.guimei.pojo.GoodsExt;

import java.util.List;
import java.util.Map;

public interface GoodsDao {

    Goods updateById(int id);

    int totalWhereRecode(Goods goods);

    List<GoodsExt> showGoods(Map<String,Object> map);

    int queryGoodsName(Goods goods);

    int updateGoods(Goods goods);

    int deleteGoods(int id);

    int add(Goods goods);
}
