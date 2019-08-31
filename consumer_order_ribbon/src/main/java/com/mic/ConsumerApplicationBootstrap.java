package com.mic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient("PROVIDER-USER")  // 启用ribbon，对PROVIDER-USER做负载均衡
public class ConsumerApplicationBootstrap
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerApplicationBootstrap.class);
    }
}
