package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * created by king on 2017/12/5
 */
@Getter@Setter
public class BaseBillDomain {
    public static final int STATU_NOMAL=0;
    public static final int STATU_SUCCESS=1;
    private Long id;

    private String sn;//订单的编码.唯一的标识
    //保存时间需要格式化
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)//不加会报错
    private Date vdate;//订单生成的日期
    //审核的状态
    private Integer status=STATU_NOMAL;//状态

    private BigDecimal totalAmount;//总数(钱)
    private BigDecimal totalNumber;//总数量,也就是商品的数量
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date auditTime;//审核的时间
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date inputTime;//录入的时间
    //查看表中的数据 录入人的id和审核人的id ,都应该是登录进来的employee 所以可以把这些数据封装到employee对象中
    private Employee inputUser;//多对一的关系,多个订单对一个录入人
    private Employee auditor;//审核的人,多个订单可以被一个人审核
}
