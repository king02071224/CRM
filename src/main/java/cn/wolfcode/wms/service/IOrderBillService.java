package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.OrderBill;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IOrderBillService {
    void deleteByPrimaryKey(Long id);

    void insert(OrderBill record);
    @Transactional(readOnly = true)
    OrderBill selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<OrderBill> selectAll();

    void updateByPrimaryKey(OrderBill record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult query(QueryObject qo);

    void updateStatus(Long id);
}
