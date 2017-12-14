package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 出库单的domain
 */
@Getter@Setter
public class StockOutComeBill extends BaseBillDomain{

    //供应商的id这一列可以封装到supplier对象中
    private Depot depot;
    //出库单对应的是多个客户
    private Client client;
    //订单里面有多个明细,一对多的关系
    private List<StockOutComeBillItem> items=new ArrayList<>();
}