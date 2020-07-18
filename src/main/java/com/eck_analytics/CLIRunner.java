package com.eck_analytics;

import com.eck_analytics.Model.Example;
import com.eck_analytics.Services.EKGAnalyze;
import com.eck_analytics.Services.ExampleService;
import com.eck_analytics.Services.LinguisticChainService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class CLIRunner implements CommandLineRunner {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // See: application.properties
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

    @Autowired
    @Bean(name="entityManagerFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();

        // See: application.properties
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.put("current_session_context_class", //
                env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));


        // Fix Postgres JPA Error:
        // Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        // Package contain entity classes
        factoryBean.setPackagesToScan(new String[] { "com.eck_analytics.Model" });
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        try {
            factoryBean.afterPropertiesSet();
        } catch(Exception ex) {
            if (ex instanceof BeanCreationException) {
                log.debug("EXCEPTION: {}", ((BeanCreationException) ex).getBeanName());
                throw ex;
            }
        }
        //
        SessionFactory sf = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sf);
        return sf;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

        return transactionManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(CLIRunner.class, args);
    }

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Lazy
    @Autowired
    ExampleService exampleService;

    @Lazy
    @Autowired
    LinguisticChainService linguisticChainService;

    @Lazy
    @Autowired
    EKGAnalyze ekgAnalyze;
    @Override
    public void run(String ... args) throws Exception {
//        exampleService.saveExamples(linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101annotations.txt"));

        System.out.println("Success");
        //Example e = new Example(10000,10000,10000,10000,10000,10000);
        //ExampleService.sa
//        try {
//            linguisticChainService.saveChainIntoFile("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100annotations.txt","/Users/annatsytsyluik/Documents/КПИ/Example_7");
//
//        }
//        catch (Exception e){}

    }

    public void getTestData(){
                linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/100annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/101annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/102.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/102annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/103.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/103annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/104.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/104annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/105.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/105annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/106.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/106annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/107.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/107annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/108.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/108annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/109.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/109annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/111.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/111annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/112.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/112annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/113.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/113annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/114.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/114annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/115.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/115annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/116.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/116annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/117.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/117annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/118.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/118annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/119.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/119annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/121.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/121annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/122.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/122annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/123.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/123annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/124.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/124annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/200.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/200annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/201.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/201annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/202.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/202annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/203.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/203annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/205.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/205annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/207.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/207annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/208.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/208annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/209.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/209annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/210.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/210annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/212.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/212annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/213.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/213annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/214.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/214annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/215.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/215annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/217.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/217annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/219.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/219annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/220.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/220annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/221.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/221annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/222.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/222annotations.txt");
        linguisticChainService.getExamplesFromFiles("/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/223.csv","/Users/annatsytsyluik/Documents/КПИ/Диплом/mitbih-database/223annotations.txt");

    }
}
