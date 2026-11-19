package com.north;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan({"com.north.system.mapper"}) // 扫描 Mapper 接口所在的包
public class NorthAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthAdminApplication.class, args);
        log.info("NorthAdminApplication started successfully: http://localhost:8080");
        log.info("doc: http://localhost:8080/doc.html");
    }

}
