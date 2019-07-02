package com.qyc.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MsgType {

    Text(1, "text", "文本消息"),
    Image(2, "image", "图片消息"),
    thumb_url(3, "link", "图文链接"),
    thumb_media_id(6, "miniprogrampage", "小程序卡片");

    private int val;
    private String code;
    private String msg;

    /**
     * 根据val，获取枚举对象
     *
     * @param val 消息编码
     * @return 枚举对象
     */
    public static MsgType fromVal(int val) {
        for (MsgType rspStatus : MsgType.values()) {
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
    public static MsgType fromVal(Boolean val) {
        int tmpVal = Boolean.TRUE.equals(val) ? 1 : 0;
        for (MsgType rspStatus : MsgType.values()) {
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
    public static MsgType fromCode(String code) {
        for (MsgType rspStatus : MsgType.values()) {
            if (rspStatus.getCode().equalsIgnoreCase(code)) {
                return rspStatus;
            }
        }
        return null;
    }

}
