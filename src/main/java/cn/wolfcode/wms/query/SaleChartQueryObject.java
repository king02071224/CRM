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
public class SaleChartQueryObject extends QueryObject{
    //全局静态的常量
    public static final Map<String,String> GROUP_BY_TYPES=new LinkedHashMap<>();
    //订单的业务时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private String productName ;
    private long clientId = -1L;
    private long brandId = -1;
    //分组的名字,通过前台传进来的name,name用来封装数据,value用来取值
    private String groupName="sm.name";
    private String groupId="sm.id";
    public Date getEndTime() {
        if (endTime != null) {
            return DateUtil.getEndDate(endTime);
        }
        return null;
    }
    //用map封装页面上分组里面的字段,可以子啊页面用map对象.key取出key值作为value,.value取出value
    public Map<String,String> getGroupByName(){
        //这里用linkedHashMap,保存有序
        GROUP_BY_TYPES.put("sm.name","销售人员");
        GROUP_BY_TYPES.put("p.name","货品名称");
        GROUP_BY_TYPES.put("p.brandName","货品品牌");
        GROUP_BY_TYPES.put("c.name","客户");
        GROUP_BY_TYPES.put("date_format(sa.vdate,'%Y-%m')","销售日期(月)");
        GROUP_BY_TYPES.put("date_format(sa.vdate,'%Y-%m-%d')","销售日期(日)");
        return GROUP_BY_TYPES;
    }

}
