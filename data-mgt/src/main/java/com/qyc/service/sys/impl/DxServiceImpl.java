package com.qyc.service.sys.impl;

import com.qyc.mapper.dc.DxMapper;
import com.qyc.model.data.Sys.SmsRequest;
import com.qyc.service.sys.DxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qianyongchao
 * @date 2019/5/28 10:47
 */
@Service
public class DxServiceImpl implements DxService {
    @Autowired
    private DxMapper dxMapper;

    /**
     * 插入短信
     *
     * @param sms 短信
     */
    @Override
    public void insertDx(SmsRequest sms) {
        dxMapper.insertDx(sms);
    }
}
