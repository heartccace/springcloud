package com.mic.controller;

import com.mic.entity.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liushuang
 * @create 2019-08-30 13:34
 */
@RestController
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient eurekaClient;
    // private String url = "http://localhost:7900/user/";
    @Value("${user.url}")
    private String url;

    @GetMapping("/order/{id}")
    public User getUser(@PathVariable Long id) {
        User user = restTemplate.getForObject(url + id, User.class);
        return user;
    }

    @GetMapping("/serverurl/{id}")
    @HystrixCommand(fallbackMethod="hystrixFail") //失败回调与本方法执行不是同一个线程，变量不能共享
    // @HystrixCommand(fallbackMethod = "stubMyService",
    //    commandProperties = {
    //      @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")
    //    }
    //) 这种方法解决变量共享问题
    public User getInfo(@PathVariable Long id) {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("provider-user", false);
        User user = restTemplate.getForObject(instance.getHomePageUrl() + "user/" + id, User.class);
        return user;
    }

    /**
     * 失败回调,返回数据类型保持一致
     * @param id
     * @return
     */
    public User hystrixFail(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("fail");
        return user;
    }
}

