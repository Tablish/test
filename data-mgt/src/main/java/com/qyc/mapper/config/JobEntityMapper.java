package com.qyc.mapper.config;

import com.qyc.model.data.quartz.JobEntity;

import java.util.List;

public interface JobEntityMapper {

    /**
     * 根据 id查找
     */
    JobEntity findById(Integer id);

    /**
     * 查找所有
     */
    List<JobEntity> findAll();


}
