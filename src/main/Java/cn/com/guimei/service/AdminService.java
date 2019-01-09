package cn.com.guimei.service;

import cn.com.guimei.pojo.Superuser;

public interface AdminService {

    Superuser login(String userLoginName, String userPassword);

    Superuser queryById(String id);

    Superuser showInfo(int id);

    int updateById(Superuser superuser);

}
