package com.isacore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringBootIsa {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIsa.class, args);
	}
	
/*	@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://192.168.4.18:1433;databaseName=isa");
        dataSource.setUsername("isa");
        dataSource.setPassword("isa2018");
        return dataSource;
    }*/
}
