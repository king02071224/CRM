package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.mapper.ChartMapper;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/12/5
 */
@Service
@SuppressWarnings("all")
public class ChartServiceImpl implements IChartService {
    @Autowired
    private ChartMapper chartMapper;
    @Override
    public List<Map<String, Object>> orderChart(QueryObject qo) {
        return chartMapper.orderChart(qo);
    }

    @Override
    public List<Map<String, Object>> saleChart(QueryObject qo) {
        return chartMapper.saleChart(qo);
    }
}
