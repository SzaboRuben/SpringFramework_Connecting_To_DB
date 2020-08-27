package spring;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
@PropertySource("classpath:/application.properties")
public class AppConfig {


//    Environment can be used for accessing the values in application.properties
    @Autowired
    private Environment environment;


    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    //            Instead of integrating the DB parameters into the source code (like the following way),
//            we use an external configuration like an application.properties which is implemented in resources
//        @Bean
//    public DataSource dataSource(){
//            MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setURL("jdbc:mysql://localhost:3307/employees?useUnicode=true");
//            dataSource.setUser("employees");
//            dataSource.setPassword("employees");
//            return dataSource;
//        }

}
