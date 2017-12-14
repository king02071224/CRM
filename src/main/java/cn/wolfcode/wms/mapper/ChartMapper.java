package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/12/5
 */
public interface ChartMapper {
    //通过查询条件去查询从前台传过来的数据,返回一个List集合
    List<Map<String,Object>> orderChart(QueryObject qo);
    List<Map<String,Object>> saleChart(QueryObject qo);
}
