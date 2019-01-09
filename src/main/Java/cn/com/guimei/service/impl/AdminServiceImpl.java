package cn.com.guimei.service.impl;

import cn.com.guimei.dao.AdminDao;
import cn.com.guimei.pojo.Superuser;
import cn.com.guimei.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName cn.com.guimei.service.impl.AdminServiceImpl
 * @Author xiaoHei
 * @Date 2018/12/30 9:59
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;
    public Superuser login(String userLoginName, String userPassword) {
        return adminDao.login(userLoginName,userPassword);
    }

    public Superuser queryById(String id) {
        return null;
    }

    public Superuser showInfo(int id) {
        return adminDao.showInfo(id);
    }

    public int updateById(Superuser superuser) {
        return 0;
    }
}
