package com.qyc.model.data;

import com.qyc.anotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author qianyongchao
 * @Description
 * @Date 2019/5/20 10:28
 * @Modified By
 */

@Getter
@Setter
public class MediaIdMessage {
    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String MediaId;

}
