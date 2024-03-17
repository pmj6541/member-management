package com.practice.manage.membermanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemberManageApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberManageApplication.class);
        app.run(args);
    }

}
