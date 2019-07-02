package com.qyc.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author qianyongchao
 * @Description
 * @Date 2019/5/9 15:25
 * @Modified By
 */
@Configuration

@PropertySource({"classpath:datasource-dev.properties", "classpath:mybatis-config.properties"})// 指定@Value字段值来源（分环境）
@MapperScan(basePackages = "com.qyc.mapper", sqlSessionFactoryRef = "dcSqlSessionFactory")
public class DcDatasourceConfig {
    //datasource-dev.properties
    @Value("${spring.datasource.dc.url}")
    private String url;
    @Value("${spring.datasource.dc.username}")
    private String userName;
    @Value("${spring.datasource.dc.password}")
    private String password;
    @Value("${spring.datasource.dc.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.dc.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.dc.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.dc.poolPreparedStatements}")
    private String poolPreparedStatements;
    @Value("${spring.datasource.dc.maxPoolPreparedStatementPerConnectionSize}")
    private String maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.dc.filters}")
    private String filters;
    @Value("${spring.datasource.dc.connectionProperties}")
    private String connectionProperties;
    //mybatis-config.properties
    @Value("${mybatis.dc.config-locations}")
    private String configLocation;
    @Value("${mybatis.dc.mapper-locations}")
    private String mapperLocation;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Primary
    @Bean("dcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dc")
    public DataSource dcDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("dcSqlSessionFactory")
    public SqlSessionFactory dcSqlSessionFactory(@Qualifier("dcDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/dc/*.xml"));
//        bean.setPlugins(new Interceptor[]{this.pageHelper()});
//        return bean.getObject();


        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // datasource配置
        bean.setDataSource(dataSource);
//        bean.setTypeAliasesPackage(typeAliasesPackage);

        bean.setPlugins(new Interceptor[]{this.pageHelper()});

        // mybatis配置
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setConfigLocation(resolver.getResource(configLocation));
            bean.setMapperLocations(resolver.getResources(mapperLocation));
            return bean.getObject();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Primary
    @Bean(name = "dcTransactionManager")
    public DataSourceTransactionManager dcTransactionManager(@Qualifier("dcDataSource") DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource);
        manager.setNestedTransactionAllowed(true);// 允许Nested嵌套子事务
        return manager;
    }

    @Primary
    @Bean("dcSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("dcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * mybatis分页配置
     * 目前使用默认配置，参数需要的情况下自行设置
     *
     * @author weibo
     * @version 2018/10/8
     */
    @Bean
    public static PageInterceptor pageHelper() {
        PageInterceptor pageHelper = new PageInterceptor();
        Properties p = new Properties();
        /*p.setProperty("offsetAsPageNum", offsetAsPageNum);
        p.setProperty("rowBoundsWithCount", rowBoundsWithCount);
        p.setProperty("pageSizeZero", "false");
        p.setProperty("reasonable", reasonable);
        p.setProperty("supportMethodsArguments", supportMethodsArguments);
        p.setProperty("countColumn", countColumn);*/
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
