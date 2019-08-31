package com.mic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderUserBootstrap
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderUserBootstrap.class);
    }
}
