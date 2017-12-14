package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.StockOutComeBill;
import cn.wolfcode.wms.domain.StockOutComeBillItem;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.StockOutComeBillItemMapper;
import cn.wolfcode.wms.mapper.StockOutComeBillMapper;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.service.IStockOutComeBillService;
import cn.wolfcode.wms.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Service
@SuppressWarnings("all")
public class StockOutComeBillServiceImpl implements IStockOutComeBillService {
    @Autowired
    private StockOutComeBillMapper stockOutComeBillMapper;
    @Autowired
    private StockOutComeBillItemMapper stockOutComeBillItem;
    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private IProductStockService productStockService;
    @Override
    public void updateByPrimaryKey(StockOutComeBill record) {
        //订单明细需要保存成本价  bill_id也就是订单的id
        record.setStatus(0);
        //再保存订单明细中的信息
        List<StockOutComeBillItem> items = record.getItems();
        BigDecimal totalNumner = BigDecimal.ZERO;
        BigDecimal totalAmout = BigDecimal.ZERO;
        for (StockOutComeBillItem item : items) {
            //先算出总数量
            totalNumner = totalNumner.add(item.getNumber());
            //再算出总价格
            totalAmout = totalNumner.multiply(item.getSalePrice());
        }
        record.setTotalAmount(totalAmout);
        record.setTotalNumber(totalNumner);
        stockOutComeBillMapper.updateByPrimaryKey(record);
        //更新明细之前,先删除之前的明细
        stockOutComeBillItem.deleteByBillId(record.getId());
        for (StockOutComeBillItem item : items) {
            BigDecimal amount = BigDecimal.ZERO;
            item.setBillId(record.getId());
            amount = amount.add(item.getNumber()).multiply(item.getSalePrice());
            item.setAmount(amount);
            stockOutComeBillItem.insert(item);
        }

    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount = stockOutComeBillMapper.queryForCount(qo);
        if (totalCount == 0) {
            return PageResult.EMPTY_PAGE;
        }
        List<StockOutComeBill> data = stockOutComeBillMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(), qo.getPageSize(), totalCount, data);
    }

    /**
     * 思路,首先去根据这个订单的id去查询这个订单的状态,如果为0那么才去审核
     * 审核的时候只需要更新审核人和审核时间,和审核状态
     *
     * @param id
     */
    @Override
    public void updateStatus(Long id) {
        //如果这个订单没有被审核过,那么才去审核(查询需要出库的的订单)
        StockOutComeBill oldBill = stockOutComeBillMapper.selectByPrimaryKey(id);

        if (oldBill.getStatus() == oldBill.STATU_NOMAL) {
            oldBill.setId(id);
            oldBill.setStatus(oldBill.STATU_SUCCESS);
            oldBill.setAuditor(UserContext.getEmployeeSession());
            oldBill.setAuditTime(new Date());
            stockOutComeBillMapper.updateStatus(oldBill);
            productStockService.stockOutComeBill(oldBill);
        }
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        //后台的删除 先删除订单,再删除订单的明细
        stockOutComeBillMapper.deleteByPrimaryKey(id);
        stockOutComeBillItem.deleteByBillId(id);

    }

    @Override
    public void insert(StockOutComeBill record) {
        //订单明细需要保存成本价  bill_id也就是订单的id
        record.setStatus(0);
        record.setInputTime(new Date());
        record.setInputUser(UserContext.getEmployeeSession());
        //再保存订单明细中的信息
        List<StockOutComeBillItem> items = record.getItems();
        BigDecimal totalNumner = BigDecimal.ZERO;
        BigDecimal totalAmout = BigDecimal.ZERO;
        for (StockOutComeBillItem item : items) {
            //先算出总数量
            totalNumner = totalNumner.add(item.getNumber());
            //再算出总价格
            totalAmout = totalNumner.multiply(item.getSalePrice());
        }
        record.setTotalAmount(totalAmout);
        record.setTotalNumber(totalNumner);
        stockOutComeBillMapper.insert(record);
        //再保存明细,
        for (StockOutComeBillItem item : items) {
            BigDecimal amount = BigDecimal.ZERO;
            item.setBillId(record.getId());
            amount = amount.add(item.getNumber()).multiply(item.getSalePrice());
            item.setAmount(amount);
            stockOutComeBillItem.insert(item);
        }


    }

    @Override
    public StockOutComeBill selectByPrimaryKey(Long id) {
        return stockOutComeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockOutComeBill> selectAll() {

        return stockOutComeBillMapper.selectAll();
    }


}
