package cn.com.guimei.service.impl;

import cn.com.guimei.dao.DiscountDao;
import cn.com.guimei.dao.GoodsDao;
import cn.com.guimei.pojo.Discount;
import cn.com.guimei.pojo.Goods;
import cn.com.guimei.pojo.GoodsExt;
import cn.com.guimei.pojo.Page;
import cn.com.guimei.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;
    @Resource
    private DiscountDao discountDao;
    public Page<GoodsExt> query(String num, Goods goods) {
        int pageNumber = 1;
        int pageSize = 3;
        if (num!=null && num.length()>0){
            pageNumber = Integer.parseInt(num);
        }
        int pageIndex = (pageNumber-1)*pageSize;

        Map<String,Object> pageMap = new HashMap<String, Object>();

        pageMap.put("goods",goods);
        pageMap.put("pageIndex",pageIndex);
        pageMap.put("pageSize",pageSize);

        Page<GoodsExt> page = new Page<GoodsExt>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(goodsDao.totalWhereRecode(goods));
        page.setPageData(goodsDao.showGoods(pageMap));
        return page;
    }

    public List<Discount> queryDis() {
        return discountDao.querryDis();
    }

    public Goods updateById(int id) {
        return goodsDao.updateById(id);
    }

    public boolean queryGoodsName(Goods goods) {
        if (goodsDao.queryGoodsName(goods)>0){
            return true;
        }
        return false;
    }

    public int updateGoods(Goods goods, MultipartFile goodsFile, String filePath) {
        //判断filePath路基是否真实存在
        File file = new File(filePath);
        if(file.exists()){
            //获取文件名称
            String goodsImageName = goodsFile.getOriginalFilename();
            //获取文件大小
            long fileSize = goodsFile.getSize();
            if(goodsImageName.toLowerCase().endsWith(".gif") ||
                    goodsImageName.toLowerCase().endsWith(".bmp") ||
                    goodsImageName.toLowerCase().endsWith(".jpeg") ||
                    goodsImageName.toLowerCase().endsWith(".icon") ||
                    goodsImageName.toLowerCase().endsWith(".jpg")
            ){
                if(fileSize<=1024*1024*5){
                    try {
                        //执行写入操作
                        goodsFile.transferTo(new File(file,"/"+goodsImageName));
                        //执行数据库写入操作
                        goods.setgImg(goodsImageName);
                        int i = goodsDao.updateGoods(goods);
                        if(i>0){
                            return 0;
                        }else{
                            //写入数据库失败
                            return 2;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    return 3;
                }
            }else{
                return 4;
            }
        }
        //写入商品图像失败(文件路径不存在)
        return 1;
    }

    public boolean deleteGoods(int id) {
        if (goodsDao.deleteGoods(id)>0){
            return true;
        }
        return false;
    }
    public int addGoods(Goods goods, MultipartFile goodsFile, String filePath) {
        //判断filePath路基是否真实存在
        File file = new File(filePath);
        if(file.exists()){
            //获取文件名称
            String goodsImageName = goodsFile.getOriginalFilename();
            //获取文件大小
            long fileSize = goodsFile.getSize();
            if(goodsImageName.toLowerCase().endsWith(".gif") ||
                    goodsImageName.toLowerCase().endsWith(".bmp") ||
                    goodsImageName.toLowerCase().endsWith(".jpeg") ||
                    goodsImageName.toLowerCase().endsWith(".icon") ||
                    goodsImageName.toLowerCase().endsWith(".jpg")
            ){
                if(fileSize<=1024*1024*5){
                    try {
                        //执行写入操作
                        goodsFile.transferTo(new File(file,"/"+goodsImageName));
                        //执行数据库写入操作
                        goods.setgImg(goodsImageName);
                        int i = goodsDao.add(goods);
                        if(i>0){
                            return 0;
                        }else{
                            //写入数据库失败
                            return 2;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    return 3;
                }
            }else{
                return 4;
            }
        }
        //写入商品图像失败(文件路径不存在)
        return 1;
    }
}
