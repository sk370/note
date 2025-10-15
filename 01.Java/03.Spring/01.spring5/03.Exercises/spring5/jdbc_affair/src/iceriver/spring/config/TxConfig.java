package iceriver.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/17 12:02
 */
@Configuration//1. 声明这是一个配置类
@ComponentScan(basePackages = "iceriver.spring")//2. 开启组件扫描
@EnableTransactionManagement//3. 声明开启事务
public class TxConfig {
//    4. 创建数据库连接池
    @Bean
    public DruidDataSource getDruidDatasource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:13306/user_db");
        dataSource.setUsername("root");
        dataSource.setPassword("dimitre123");
        return dataSource;
    }

//    4. 创建jadbc Template对象
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(getDruidDatasource());//注入dataSource，但这种方式会执行两次getDruidDatasource
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
//    6. 创建事务管理器对象
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
