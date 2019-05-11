package com.alipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author renzhiqiang
 * @Description 转账服务启动器
 * @Date 2019-04-10
 **/
@SpringBootApplication
@EnableScheduling
public class TransferApplication {
    public static void main(String[] args){
        SpringApplication.run(TransferApplication.class, args);
    }
}
