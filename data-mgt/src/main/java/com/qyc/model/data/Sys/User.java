package com.qyc.model.data.Sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author qianyongchao
 * @date 2019/5/27 18:51
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 3872180212482072119L;
    private String id;// 用户ID（自动增长）
    private String nick_name;// 昵称
    private String pwd;// 登录密码
    private String salt;// 密码盐值
    private String name;// 姓名
    private String phone;// 手机
    private String email;// 电子邮箱
}
