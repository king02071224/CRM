package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface ISystemMenuService {
    void deleteByPrimaryKey(Long id);

    void insert(SystemMenu record);
    @Transactional(readOnly = true)
    SystemMenu selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<SystemMenu> selectAll();

    void updateByPrimaryKey(SystemMenu record);
    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult qury(QueryObject qo);

    /**
     * 通过父类的sn编码,去查询当前父菜单下面的子菜单
     * @param parentSn
     * @return
     */
    @Transactional(readOnly = true)
    List<Map<String,Object>> selectMenuByParentSn(String parentSn);
}
