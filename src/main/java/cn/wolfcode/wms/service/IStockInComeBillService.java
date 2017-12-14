package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IStockInComeBillService {
    void deleteByPrimaryKey(Long id);

    void insert(StockInComeBill record);
    @Transactional(readOnly = true)
    StockInComeBill selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<StockInComeBill> selectAll();

    void updateByPrimaryKey(StockInComeBill record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult query(QueryObject qo);

    void updateStatus(Long id);
}
