package com.minbeom.familyintroductionbackendrenew;

import com.minbeom.familyintroductionbackendrenew.repository.MysqlBoardRepository;
import com.minbeom.familyintroductionbackendrenew.repository.UserRepository;
import com.minbeom.familyintroductionbackendrenew.repository.MysqlUserRepository;
import com.minbeom.familyintroductionbackendrenew.service.BoardService;
import com.minbeom.familyintroductionbackendrenew.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public UserRepository userRepository() {
        return new MysqlUserRepository(dataSource());
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public BoardService boardService() {
        return new BoardService(mysqlBoardRepository());
    }

    @Bean
    public MysqlBoardRepository mysqlBoardRepository() {
        return new MysqlBoardRepository(dataSource());
    }
}
