package cn.com.guimei.service.impl;

import cn.com.guimei.dao.SmallClassDao;
import cn.com.guimei.pojo.Page;
import cn.com.guimei.pojo.SmallClassExt;
import cn.com.guimei.pojo.Smallclass;
import cn.com.guimei.service.SmallClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmallClassServiceImpl implements SmallClassService {

    @Resource
    private SmallClassDao smallClassDao;
    public Page<SmallClassExt> query(String num, Smallclass smallclass) {
        int pageNumber = 1;
        int pageSize = 3;
        if (num!=null && num.length()>0){
            pageNumber = Integer.parseInt(num);
        }
        int pageIndex = (pageNumber-1)*pageSize;

        Map<String,Object>pageMap = new HashMap<String, Object>();

        pageMap.put("sc",smallclass);
        pageMap.put("pageIndex",pageIndex);
        pageMap.put("pageSize",pageSize);

        Page<SmallClassExt> page = new Page<SmallClassExt>();
        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setTotalRecode(smallClassDao.totalWhereRecode(smallclass));
        page.setPageData(smallClassDao.showSmallclass(pageMap));
        return page;
    }

    public SmallClassExt updateById(int id) {
        return smallClassDao.updateById(id);
    }

    public List<Smallclass> queryName(String smBId) {
        int id = smBId!=null && smBId.length()>0?Integer.parseInt(smBId):0;
        return smallClassDao.queryName(id);
    }

    public boolean smallClassUpdateById(Smallclass smallclass) {
        if (smallclass.getId()>0 && smallClassDao.updateSmallclass(smallclass)>0){
            return true;
        }
        return false;
    }

    public int smallClassDelete(int id) {
        return smallClassDao.deleteSmallclass(id);
    }

    public int smallClassAdd(Smallclass smallclass) {
        return 0;
    }
}
