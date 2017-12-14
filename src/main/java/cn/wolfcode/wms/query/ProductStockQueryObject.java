package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 * created by king on 2017/12/5
 */
@Getter@Setter
public class ProductStockQueryObject extends QueryObject {
    private String keywords;
    private long depotId=-1L;
    private long brandId=-1L;
    private Integer maxNumber;

}
