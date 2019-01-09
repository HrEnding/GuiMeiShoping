package cn.com.guimei.dao;

import cn.com.guimei.pojo.Superuser;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {

    Superuser login(@Param("userLoginName") String userLoginName,@Param("userPassword") String userPassword);

    Superuser queryById(String id);

    Superuser showInfo(@Param("id") int id);

    int updateById(Superuser superuser);

}
