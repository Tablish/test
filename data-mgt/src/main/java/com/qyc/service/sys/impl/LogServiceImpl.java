package com.qyc.service.sys.impl;

import com.qyc.mapper.dc.LogMapper;
import com.qyc.model.data.Sys.Log;
import com.qyc.service.sys.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qianyongchao
 * @date 2019/5/28 9:47
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    /**
     * 生成日志
     *
     * @param log
     */
    @Override
    public void insertLog(Log log) {
        logMapper.insertLog(log);
    }
}
