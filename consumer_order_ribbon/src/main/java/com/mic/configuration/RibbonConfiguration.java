package com.mic.configuration;

import com.mic.annotation.ExcludeConponentScan;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liushuang
 * @create 2019-08-31 16:42
 */
@Configuration
@ExcludeConponentScan
public class RibbonConfiguration {
    /**
     * 自定义策略
     * @return
     */

    @Bean
    public IRule ribbonRule(/*IClientConfig config*/) {
        return new RandomRule();
    }
}
