package com.qyc.model.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author qianyongchao
 * @Description
 * @Date 2019/5/9 10:35
 * @Modified By
 */
@Getter
@Setter
@ToString
public class Product {
    private String id;
    private String catagory_id;
    private Integer price_strategy;//是否区分定价 0 否 1 是
    private BigDecimal uniform_price;
    private BigDecimal uniform_real_price;
    private BigDecimal male_price;
    private BigDecimal male_real_price;
    private BigDecimal female_price;
    private BigDecimal female_real_price;
    private BigDecimal female_married_price;
    private BigDecimal female_marreid_real_price;
    private Integer exclusive;
    private Integer retail;
    private Integer append;
    private Integer product_type;
    private Integer support_age;
    private Integer support_card;
    private Integer report_send;
    private String description;
    private String attention;
    private String instruction;
    private Integer status;
    private Date created;
    private Date updated;
    private String operator;
    private String operator_id;
    private String name;
    private String picture_url;
    private String min_age;
    private String max_age;
    private Integer sales;
    private String support_sex;
    private String isupgrade;
    private String vedio_url;
    private Integer sort;
    private Integer company_card_isupgrade;
    private String interior_name;
    private BigDecimal interior_price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date valid_start_date; //套餐有效期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date valid_end_date; //套餐有效期

    private Boolean product_list_show = false; //套餐直约显示
    private Boolean shop_list_show = false; //门店直约显示

    private String suit_version; //适用版本（卡类用到）

    private BigDecimal exchange_integral; //积分兑换额度

    private Integer support_groupbuying; //是否支持团购

    private Integer active_flag; //后台下单可用

}
