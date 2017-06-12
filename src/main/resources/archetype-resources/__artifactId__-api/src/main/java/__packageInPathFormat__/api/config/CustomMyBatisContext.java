/**
 * 
 */
package ${package}.api.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mike
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(annotationClass = Mapper.class, basePackages = "${package}", sqlSessionFactoryRef = "sqlSessionFactoryBean")
public class CustomMyBatisContext {

    /**
     * myBatis의 {@link org.apache.ibatis.session.SqlSessionFactory}을 생성하는 팩토리빈을 등록한다.
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext)
            throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        // 마이바티스가 사용한 DataSource를 등록
        factoryBean.setDataSource(dataSource);
        // 마이바티스 프로퍼티 설정
        Properties mybatisProperties = new Properties();
        mybatisProperties.setProperty("lazyLoadingEnabled", "true");
        mybatisProperties.setProperty("aggressiveLazyLoading", "false");
        mybatisProperties.setProperty("lazyLoadTriggerMethods", "");
        mybatisProperties.setProperty("mapUnderscoreToCamelCase", "true");
        factoryBean.setConfigurationProperties(mybatisProperties);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis/configuration.xml"));
        factoryBean.setTypeAliasesPackage("${package}.core.mybatis.model");
        factoryBean.setTypeHandlersPackage("${package}.api.common.mybatis.handler");
        return factoryBean;
    }

}
