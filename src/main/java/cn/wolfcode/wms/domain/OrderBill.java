package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单的domain模型
 */
@Getter@Setter@ToString
public class OrderBill extends BaseBillDomain{
    //供应商的id这一列可以封装到supplier对象中
    private Supplier supplier;
    //订单里面有多个明细,一对多的关系
    private List<OrderBillItem> items=new ArrayList<>();
}