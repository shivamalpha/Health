package com.develop.recruitmentstatics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


// This is the entry point of our application .
@ComponentScan(value = "${app.packagesToScan}")
@SpringBootApplication
public class RecruitmentStaticsApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(RecruitmentStaticsApplication.class, args);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
