package cn.wolfcode.wms.service;

import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/11/26
 */
public interface IChartService {

    List<Map<String,Object>> orderChart(QueryObject qo);
    List<Map<String,Object>> saleChart(QueryObject qo);

}
