package com.qyc.service.config.impl;

import com.qyc.model.data.quartz.JobEntity;

import java.util.List;

public interface JobEntityService {
    JobEntity findById(Integer id);

    List<JobEntity> findAll();
}
