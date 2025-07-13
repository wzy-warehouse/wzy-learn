package cn.com.wzy.web.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@MapperScan("cn.com.wzy.web.management.mapper")
@EnableCaching
public class PersonManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }

}
