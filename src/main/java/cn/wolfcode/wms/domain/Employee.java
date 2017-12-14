package cn.wolfcode.wms.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class Employee extends BaseDomain{

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin=false;

    private Department dept;
    /**
     * 多对对的关系,一个员工可以有多个角色
     */
    private List<Role> roles=new ArrayList<>();

}