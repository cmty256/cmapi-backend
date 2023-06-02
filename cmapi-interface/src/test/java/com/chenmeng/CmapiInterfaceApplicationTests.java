package com.chenmeng;

import com.chenmeng.sdk.client.CmApiClient;
import com.chenmeng.sdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CmapiInterfaceApplicationTests {
    
    @Resource
    private CmApiClient cmApiClient;

    @Test
    void contextLoads() {
    }

    /**
     * 测试api客户端
     */
    @Test
    void testApiClient() {
        String result1 = cmApiClient.getNameByGet("沉梦");
        String result2 = cmApiClient.getNameByPost("听雨");
        User user = new User();
        user.setName("沉梦听雨");
        String result3 = cmApiClient.getNameByUser(user);
        System.out.println(result1);
        System.out.println("------");
        System.out.println(result2);
        System.out.println("------");
        System.out.println(result3);
    }

    @Test
    void testApiClient2() {
        User user = new User();
        user.setName("沉梦听雨");
        String result3 = cmApiClient.getNameByUser(user);
        System.out.println(result3);
    }

}
