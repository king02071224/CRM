package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.*;
import cn.wolfcode.wms.exception.StockOutComeBillException;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleAccountMapper;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Service
@SuppressWarnings("all")
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;

    @Autowired
    private SaleAccountMapper saleAccountMapper;
    @Override
    public void updateByPrimaryKey(ProductStock record) {
        productStockMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int totalCount=productStockMapper.queryForCount(qo);
        if (totalCount==0){
            return PageResult.EMPTY_PAGE;
        }
        List<ProductStock> data=productStockMapper.queryForList(qo);
        return new PageResult(qo.getCurrentPage(),qo.getPageSize(),totalCount,data);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        productStockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(ProductStock record) {
        productStockMapper.insert(record);
    }

    @Override
    public ProductStock selectByPrimaryKey(Long id) {
        return productStockMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductStock> selectAll() {

        return productStockMapper.selectAll();
    }

    @Override
    public void stockInComeBill(StockInComeBill oldBill) {
        //如果库存中有这个商品,那就更新,没有就增加库存
        List<StockInComeBillItem> items = oldBill.getItems();
        for (StockInComeBillItem item : items) {
            ProductStock productStock=productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(),oldBill.getDepot().getId());
            //通过商品的id和仓库的id快来查询是否存在该商品
            if (productStock == null) {
                productStock=new ProductStock();
                //该商品不存在,直接插入这个商品
                productStock.setAmount(item.getAmount());
                productStock.setStoreNumber(item.getNumber());
                productStock.setPrice(item.getCostPrice());
                productStock.setDepot(oldBill.getDepot());
                productStock.setProduct(item.getProduct());
                productStockMapper.insert(productStock);
            }else {
                //否则数据库中有这个商品,那么我们得去更新数据库中的数据,移动加权平均法
                BigDecimal totalAmount = productStock.getAmount().add(item.getAmount());
                BigDecimal totalNumber = productStock.getStoreNumber().add(item.getNumber());
                //求出进货价=
                BigDecimal costPrice = totalAmount.divide(totalNumber);
                productStock.setPrice(costPrice);
                productStock.setStoreNumber(totalNumber);
                productStock.setAmount(totalAmount);
                productStockMapper.updateByPrimaryKey(productStock);
            }
        }
    }

    @Override
    public void stockOutComeBill(StockOutComeBill oldBill) {
        List<StockOutComeBillItem> items = oldBill.getItems();
        for (StockOutComeBillItem item : items) {
            ProductStock productStock = productStockMapper.selectByProductIdAndDepotId(item.getProduct().getId(), oldBill.getDepot().getId());
            //通过商品的id和仓库的id快来查询是否存在该商品
            if (productStock == null) {
                throw new StockOutComeBillException("这个["+item.getProduct().getName()+"]没有库存了");
            } else if (item.getNumber().compareTo(productStock.getStoreNumber()) > 0) {
                throw new StockOutComeBillException("这个["+item.getProduct().getBrandName()+"]库存["+productStock.getStoreNumber()+"]不足["
                        +item.getNumber()+"]"
                );
            } else {
                //如果有库存,那么就去更新数据库中的数据
                BigDecimal currentNumber = item.getNumber();
                BigDecimal newNumber = productStock.getStoreNumber().subtract(currentNumber);
                BigDecimal newAmount = newNumber.multiply(productStock.getPrice());
                productStock.setAmount(newAmount);
                productStock.setStoreNumber(newNumber);
                productStockMapper.updateByPrimaryKey(productStock);

                //这里把数据存入到销售账中
                SaleAccount saleAccount = new SaleAccount();
                saleAccount.setClient(oldBill.getClient());
                saleAccount.setVdate(oldBill.getVdate());
                saleAccount.setNumber(item.getNumber());
                saleAccount.setCostPrice(productStock.getPrice());
                saleAccount.setCostAmount(saleAccount.getNumber().multiply(saleAccount.getCostPrice()));
                saleAccount.setSalePrice(item.getSalePrice());
                saleAccount.setSaleAmount(saleAccount.getNumber().multiply(saleAccount.getSalePrice()));
                saleAccount.setProduct(item.getProduct());
                saleAccount.setSaleman(oldBill.getInputUser());
                saleAccount.setClient(oldBill.getClient());
                saleAccountMapper.insert(saleAccount);
            }
        }
    }
}
