package cn.wolfcode.wms.query;

import cn.wolfcode.wms.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * created by king on 2017/12/5
 */
@Getter@Setter
public class OrderChartQueryObject extends QueryObject{
    //订单的业务时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String productName ;
    private long supplierId = -1L;
    private long brandId = -1;
    //分组的名字,通过前台传进来的name,name用来封装数据,value用来取值
    private String groupName="e.name";
    private String groupId="e.id";
    public Date getEndTime() {
        if (endTime != null) {
            return DateUtil.getEndDate(endTime);
        }
        return null;
    }
    //用map封装页面上分组里面的字段,可以子啊页面用map对象.key取出key值作为value,.value取出value
    public Map<String,String> getGroupByName(){
        //这里用linkedHashMap,保存有序
        Map<String,String> map=new LinkedHashMap<>();
        map.put("e.name","订货人员");
        map.put("p.name","货品名称");
        map.put("s.name","供应商");
        map.put("p.brandName","品牌");
        map.put("date_format(bill.vdate,'%Y-%m')","订货日期(月)");
        map.put("date_format(bill.vdate,'%Y-%m-%d')","订货日期(日)");
        return map;
    }

}
