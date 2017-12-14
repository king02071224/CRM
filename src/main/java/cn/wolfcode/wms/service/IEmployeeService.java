package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.page.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by king on 2017/11/26
 */
@Transactional
public interface IEmployeeService {
    void deleteByPrimaryKey(Long id);

    void insert(Employee record,Long[] ids);

    Employee selectByPrimaryKey(Long id);
    @Transactional(readOnly = true)
    List<Employee> selectAll();

    void updateByPrimaryKey(Employee record,Long[] ids);
    //根据查询条件去封装结果集
    PageResult qury(QueryObject qo);
    @Transactional(readOnly = true)
    void ckeckLogin(String name, String password);

    void bachDelete(Long[] ids);
}
