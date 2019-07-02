package com.qyc.model.data.Sys;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author qianyongchao
 * @date 2019/5/28 9:38
 */
@Getter
@Setter
public class Log {

    private String id; //日志id

    private String operator; //操作人

    private Integer type; //日志类型

    private String remark; //记录

    private Date created; //插入时间

    private Date updated; //更新时间
}
