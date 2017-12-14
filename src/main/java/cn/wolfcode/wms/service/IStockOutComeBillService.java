package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockOutComeBill;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IStockOutComeBillService {
    void deleteByPrimaryKey(Long id);

    void insert(StockOutComeBill record);
    @Transactional(readOnly = true)
    StockOutComeBill selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<StockOutComeBill> selectAll();

    void updateByPrimaryKey(StockOutComeBill record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult query(QueryObject qo);

    void updateStatus(Long id);
}
