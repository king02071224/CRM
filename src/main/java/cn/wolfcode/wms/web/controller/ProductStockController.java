package cn.wolfcode.wms.web.controller;

import cn.wolfcode.wms.annotation.RequiredPermission;
import cn.wolfcode.wms.domain.Depot;
import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.service.IBrandService;
import cn.wolfcode.wms.service.IDepotService;
import cn.wolfcode.wms.service.IProductStockService;
import cn.wolfcode.wms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("productStock")
public class ProductStockController {
    @Autowired
    private IProductStockService productStockService;
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IBrandService brandService;
    @RequiredPermission("库存的列表")
    @RequestMapping("query")
    public String query(Model model, @ModelAttribute("qo") ProductStockQueryObject qo) throws Exception {
        //查询所有的仓库
        List<Depot> depots = depotService.selectAll();
        model.addAttribute("depots",depots);
        model.addAttribute("brands",brandService.selectAll());
        PageResult result = productStockService.query(qo);
        model.addAttribute("result", result);
        return "productStock/list";
    }
    @RequiredPermission("库存的编辑")
    @RequestMapping("input")
    public String input(Model model, Long id) throws Exception {
        if (id != null) {
            ProductStock productStock = productStockService.selectByPrimaryKey(id);
            model.addAttribute("productStock", productStock);
        }
        return "productStock/input";
    }
    @RequiredPermission("库存的保存/更改")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Model model, ProductStock entity) throws Exception {
        try {
            if (entity.getId() != null) {
                productStockService.updateByPrimaryKey(entity);
            } else {
                productStockService.insert(entity);
            }
            return new JsonResult(true,"productStock");
        } catch (Exception e) {
            e.printStackTrace();
            return  new JsonResult("保存失败");
        }
    }
    @RequiredPermission("库存的删除")
    @ResponseBody
    @RequestMapping("delete")
    public JsonResult delete(Long id) throws Exception {
        try {
            if (id != null) {
                productStockService.deleteByPrimaryKey(id);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult("删除失败");
        }

    }
}
