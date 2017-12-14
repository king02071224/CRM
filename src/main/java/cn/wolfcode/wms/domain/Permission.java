package cn.wolfcode.wms.domain;

import lombok.*;

@Getter
@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class Permission extends BaseDomain{

    private String name;

    private String expression;

}