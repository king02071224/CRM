package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.StockInComeBill;
import cn.wolfcode.wms.domain.StockOutComeBill;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IProductStockService {
    void deleteByPrimaryKey(Long id);

    void insert(ProductStock record);
    @Transactional(readOnly = true)
    ProductStock selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<ProductStock> selectAll();

    void updateByPrimaryKey(ProductStock record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult query(QueryObject qo);
    public void stockInComeBill(StockInComeBill oldBill);
    public void stockOutComeBill(StockOutComeBill oldBill);
}
