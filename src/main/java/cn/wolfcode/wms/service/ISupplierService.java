package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Supplier;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface ISupplierService {
    void deleteByPrimaryKey(Long id);

    void insert(Supplier record);
    @Transactional(readOnly = true)
    Supplier selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<Supplier> selectAll();

    void updateByPrimaryKey(Supplier record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult qury(QueryObject qo);
}
