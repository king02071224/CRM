package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IDepotService {
    void deleteByPrimaryKey(Long id);

    void insert(Depot record);

    Depot selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<Depot> selectAll();

    void updateByPrimaryKey(Depot record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult qury(QueryObject qo);
}
