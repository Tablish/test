package com.qyc.mapper.dc;

import com.qyc.model.data.Sys.SmsRequest;

public interface DxMapper {

    /**
     * @param sms
     */
    void insertDx(SmsRequest sms);
}
