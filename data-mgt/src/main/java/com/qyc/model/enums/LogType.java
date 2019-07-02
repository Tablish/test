package com.qyc.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qianyongchao
 * @date 2019/5/28 9:59
 */
@Getter
@AllArgsConstructor
public enum LogType {

    Dx(1, "1", "短信服务"),
    Sys(2, "2", "信息服务");
    private int val;
    private String code;
    private String msg;

    /**
     * 根据val，获取枚举对象
     *
     * @param val 消息编码
     * @return 枚举对象
     */
    public static LogType fromVal(int val) {
        for (LogType rspStatus : LogType.values()) {
            if (rspStatus.getVal() == val) {
                return rspStatus;
            }
        }
        return null;
    }

    /**
     * 根据val，获取枚举对象
     *
     * @param val 消息编码
     * @return 枚举对象
     */
    public static LogType fromVal(Boolean val) {
        int tmpVal = Boolean.TRUE.equals(val) ? 1 : 0;
        for (LogType rspStatus : LogType.values()) {
            if (rspStatus.getVal() == tmpVal) {
                return rspStatus;
            }
        }
        return null;
    }

    /**
     * 根据code，获取枚举对象
     *
     * @param code 消息编码
     * @return 枚举对象
     */
    public static LogType fromCode(String code) {
        for (LogType rspStatus : LogType.values()) {
            if (rspStatus.getCode().equalsIgnoreCase(code)) {
                return rspStatus;
            }
        }
        return null;
    }

}
