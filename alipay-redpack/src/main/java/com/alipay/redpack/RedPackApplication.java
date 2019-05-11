package com.alipay.redpack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author renzhiqiang
 * @Description 红包服务启动器
 * @Date 2019-04-10
 **/
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class RedPackApplication {
    public static void main(String[] args){
        SpringApplication.run(RedPackApplication.class, args);
    }
}
