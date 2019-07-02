package com.qyc.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 所有定义的job类型
 */
@Getter
@AllArgsConstructor
public enum JobType {


    TEST(1, "1", "DynamicJob"),
    Dx(2, "2", "DxJob");
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
