package com.qyc.model.data.quartz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author qianyongchao
 * @date 2019/5/28 13:44
 */

@Entity
@Table(name = "JOB_ENTITY")
@Getter
@Setter
@ToString
public class JobEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;          //job名称
    private String group;         //job组名
    private String cron;          //执行的cron
    private String parameter;     //job的参数
    private String description;   //job描述信息
    @Column(name = "vm_param")
    private String vmParam;       //vm参数
    @Column(name = "jar_path")
    private String jarPath;       //job的jar路径,在这里我选择的是定时执行一些可执行的jar包
    private String status;        //job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job

    public JobEntity() {
    }

    //新增Builder模式,可选,选择设置任意属性初始化对象
    public JobEntity(Builder builder) {
        id = builder.id;
        name = builder.name;
        group = builder.group;
        cron = builder.cron;
        parameter = builder.parameter;
        description = builder.description;
        vmParam = builder.vmParam;
        jarPath = builder.jarPath;
        status = builder.status;
    }

    public static class Builder {
        private Integer id;
        private String name = "";          //job名称
        private String group = "";         //job组名
        private String cron = "";          //执行的cron
        private String parameter = "";     //job的参数
        private String description = "";   //job描述信息
        private String vmParam = "";       //vm参数
        private String jarPath = "";       //job的jar路径
        private String status = "";        //job的执行状态,只有该值为OPEN才会执行该Job

        public Builder withId(Integer i) {
            id = i;
            return this;
        }

        public Builder withName(String n) {
            name = n;
            return this;
        }

        public Builder withGroup(String g) {
            group = g;
            return this;
        }

        public Builder withCron(String c) {
            cron = c;
            return this;
        }

        public Builder withParameter(String p) {
            parameter = p;
            return this;
        }

        public Builder withDescription(String d) {
            description = d;
            return this;
        }

        public Builder withVMParameter(String vm) {
            vmParam = vm;
            return this;
        }

        public Builder withJarPath(String jar) {
            jarPath = jar;
            return this;
        }

        public Builder withStatus(String s) {
            status = s;
            return this;
        }

        public JobEntity newJobEntity() {
            return new JobEntity(this);
        }
    }
}
