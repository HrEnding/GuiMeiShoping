package cn.com.guimei.dao;

import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SmallClassDao {

    int totalWhereRecode(Smallclass smallclass);

    List<SmallClassExt> showSmallclass(Map<String,Object> map);

    SmallClassExt updateById(int id);

    List<Smallclass>queryName();

    int updateSmallclass(Smallclass smallclass);

    int addSmallclass(Smallclass smallclass);

    int deleteSmallclass(int id);

}
