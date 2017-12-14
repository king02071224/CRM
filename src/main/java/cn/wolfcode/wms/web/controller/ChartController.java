package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequiredPermission;
import cn.wolfcode.wms.query.OrderChartQueryObject;
import cn.wolfcode.wms.query.SaleChartQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IChartService;
import cn.wolfcode.wms.service.IClientService;
import cn.wolfcode.wms.service.ISupplierService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/11/26
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("chart")
public class ChartController {
    @Autowired
    IChartService chartService;
    @Autowired
    ISupplierService supplierService;
    @Autowired
    IBrandService brandService;
    @Autowired
    IClientService clientService;

    @RequiredPermission("采购订单列表")
    @RequestMapping("order")
    public String order(Model model, @ModelAttribute("qo") OrderChartQueryObject qo) throws Exception {
        //查出数据库中所有的供应商
        model.addAttribute("suppliers", supplierService.selectAll());
        //查出数据库中所有的品牌
        model.addAttribute("brands", brandService.selectAll());
        List<Map<String, Object>> list = chartService.orderChart(qo);
        model.addAttribute("result", list);
        return "/chart/orderChart";
    }

    @RequiredPermission("销售订单列表")
    @RequestMapping("sale")
    public String sale(Model model, @ModelAttribute("qo") SaleChartQueryObject qo) throws Exception {
        //查出数据库中所有的品牌
        model.addAttribute("brands", brandService.selectAll());
        //查出所有的客户
        model.addAttribute("clients", clientService.selectAll());
        //查询分组之后的数据,装到一个Map中,并map封装到list的集合中
        List<Map<String, Object>> list = chartService.saleChart(qo);
        model.addAttribute("result", list);
        return "/chart/saleChart";
    }
    @RequestMapping("saleChartsByBar")
    public String saleChartsByBar(Model model, @ModelAttribute("qo") SaleChartQueryObject qo) throws Exception {
        //分析怎么把数据响应给柱状图的界面
        List<Map<String, Object>> list = chartService.saleChart(qo);
        List<String> groupByName=new ArrayList<>();
        List<String> totalAmounts=new ArrayList<>();
        //求出销售额最低和最高的
        BigDecimal max=BigDecimal.ZERO;
        for (Map<String, Object> map : list) {
            groupByName.add(map.get("groupBy").toString());
            totalAmounts.add(map.get("totalAmount").toString());
            BigDecimal currentAmount=new BigDecimal(map.get("totalAmount").toString());
            if (currentAmount.compareTo(max)>=0) {
                max=currentAmount;
            }
        }
        String groupType = qo.getGroupByName().get(qo.getGroupName());
        model.addAttribute("groupType",groupType);
        model.addAttribute("max",max);
        model.addAttribute("groupByName", JSON.toJSONString(groupByName));
        model.addAttribute("totalAmounts", JSON.toJSONString(totalAmounts));
        return "/chart/saleChartsByBar";//这里会跳到柱状图的jsp,弹出窗口,用到artDiolog,iframTools
    }
    @RequestMapping("saleChartsByPie")
    public String saleChartsByPie(Model model, @ModelAttribute("qo") SaleChartQueryObject qo) throws Exception {
        //分析怎么把数据响应给柱状图的界面
        List<Map<String, Object>> list = chartService.saleChart(qo);
        List<String> groupByName=new ArrayList<>();
        List<String> totalAmounts=new ArrayList<>();
        //求出销售额最低和最高的
        BigDecimal max=BigDecimal.ZERO;
        List<Map<String,String>> nameAndTotalAmount=new ArrayList<>();
        for (Map<String, Object> map : list) {
            Map<String,String> maps=new HashMap<>();
            groupByName.add(map.get("groupBy").toString());
            totalAmounts.add(map.get("totalAmount").toString());
            //-----------------------
            maps.put("value",map.get("totalAmount").toString());
            maps.put("name",map.get("groupBy").toString());
            nameAndTotalAmount.add(maps);
            BigDecimal currentAmount=new BigDecimal(map.get("totalAmount").toString());
            if (currentAmount.compareTo(max)>=0) {
                max=currentAmount;
            }
        }
        String groupType = qo.getGroupByName().get(qo.getGroupName());
        model.addAttribute("groupType",groupType);
        model.addAttribute("max",max);
        model.addAttribute("groupByName", JSON.toJSONString(groupByName));
        model.addAttribute("totalAmounts", JSON.toJSONString(totalAmounts));
        model.addAttribute("nameAndTotalAmount", JSON.toJSONString(nameAndTotalAmount));
        System.out.println(JSON.toJSONString(nameAndTotalAmount));
        return "/chart/saleChartsByPie";//这里会跳到柱状图的jsp,弹出窗口,用到artDiolog,iframTools
    }
}
