package com.qyc.service.config.impl;

import com.qyc.mapper.config.JobEntityMapper;
import com.qyc.model.data.quartz.JobEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qianyongchao
 * @date 2019/5/28 13:51
 */
@Service
public class JobEntityServiceImpl implements JobEntityService {
    @Autowired
    private JobEntityMapper jobEntityMapper;

    @Override
    public JobEntity findById(Integer id) {
        return jobEntityMapper.findById(id);
    }

    @Override
    public List<JobEntity> findAll() {
        return jobEntityMapper.findAll();
    }
}
