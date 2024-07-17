package com.ly.highmyopia;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class HighmyopiaApplication {
    public static void main(String[] args) {
        /**
         * 高度近视项目程序入口
         * date: 2020年2月10日
         * author: liangyue
         * data: 2024年1月16
         */
        SpringApplication.run(HighmyopiaApplication.class, args);
    }

}
