package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Permission;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IPermissionService {
    void deleteByPrimaryKey(Long id);


    Permission selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<Permission> selectAll();

    @Transactional(readOnly = true)
    //根据查询条件去封装结果集
    PageResult qury(QueryObject qo);

    void reload();
}
