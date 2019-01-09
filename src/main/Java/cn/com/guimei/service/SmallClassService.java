package cn.com.guimei.service;

import cn.com.guimei.pojo.Page;
import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;

import java.util.List;

public interface SmallClassService {

    Page<SmallClassExt>query(String pageNumber, Smallclass smallclass);

    SmallClassExt updateById(int id);

    List<Smallclass>queryName();

    boolean smallClassUpdateById(Smallclass smallclass);

    int smallClassDelete(int id);

    int smallClassAdd(Smallclass smallclass);
}
