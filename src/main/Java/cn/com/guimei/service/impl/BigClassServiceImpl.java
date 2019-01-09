package cn.com.guimei.service.impl;

import cn.com.guimei.dao.BigClassDao;
import cn.com.guimei.pojo.BigClass;
import cn.com.guimei.service.BigClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BigClassServiceImpl implements BigClassService {

    @Resource
    private BigClassDao bigClassDao;

    public List<BigClass> queryBCName() {
        return bigClassDao.queryBCName();
    }

}
