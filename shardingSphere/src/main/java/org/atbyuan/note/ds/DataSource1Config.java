package org.atbyuan.note.ds;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.encrypt.api.EncryptColumnRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptTableRuleConfiguration;
import org.apache.shardingsphere.encrypt.api.EncryptorRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;

/**
 * @author atbyuan
 * @since 2021/9/4 14:06
 **/
@Configuration
@MapperScan(basePackages = "org.atbyuan.note.mapper.ds1", sqlSessionTemplateRef = "sqlSessionTemplateDs1")
public class DataSource1Config {

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds1")
    public static class DataSourceConfig1 {
        private String driverClassName;
        private String jdbcUrl;
        private String username;
        private String password;
        private Integer maxTotal;
    }

    @Bean(name = "dataSourceDs1")
    @Primary
    public DataSource dataSourceBuilder(@Autowired DataSourceConfig1 dsConfig) throws SQLException {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(dsConfig.getJdbcUrl());
        configuration.setDriverClassName(dsConfig.getDriverClassName());
        configuration.setUsername(dsConfig.getUsername());
        configuration.setPassword(dsConfig.getPassword());
        configuration.setMaximumPoolSize(dsConfig.getMaxTotal());
        return EncryptDataSourceFactory.createDataSource(new HikariDataSource(configuration), getEncryptRuleConfiguration(), new Properties());
    }

    private static EncryptRuleConfiguration getEncryptRuleConfiguration() {
        Properties props = new Properties();
        EncryptColumnRuleConfiguration columnConfig = new EncryptColumnRuleConfiguration("", "email", "", "encryptor");
        EncryptTableRuleConfiguration tableConfig = new EncryptTableRuleConfiguration(Collections.singletonMap("email", columnConfig));
        EncryptRuleConfiguration encryptRuleConfig = new EncryptRuleConfiguration();

        encryptRuleConfig.getEncryptors().put("encryptor", new EncryptorRuleConfiguration("own", props));
        encryptRuleConfig.getTables().put("ss_user", tableConfig);
        return encryptRuleConfig;
    }

    @Bean(name = "sqlSessionFactoryDs1")
    @Primary
    public SqlSessionFactory jdsSqlSessionFactory(@Qualifier("dataSourceDs1") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/ds1/*.xml"));
        bean.setTypeAliasesPackage("org.atbyuan.note.entity");

        org.apache.ibatis.session.Configuration configuration = Objects.requireNonNull(bean.getObject()).getConfiguration();
        if (Objects.nonNull(configuration)) {
            configuration.setMapUnderscoreToCamelCase(true);
        }
        return bean.getObject();
    }

    @Bean(name = "transactionManagerDs1")
    @Primary
    public DataSourceTransactionManager jdsTransactionManager(@Qualifier("dataSourceDs1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplateDs1")
    @Primary
    public SqlSessionTemplate jdsSqlSessionTemplate(@Qualifier("sqlSessionFactoryDs1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}