package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Client extends BaseDomain{

    private String name;

    private String sn;

    private String phone;

}