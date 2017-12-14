package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Supplier extends BaseDomain{

    private String name;

    private String phone;

    private String address;


}