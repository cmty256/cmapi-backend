package com.chenmeng;

import com.chenmeng.client.CmApiClient;
import com.chenmeng.model.User;
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
        String result3 = cmApiClient.getNameByPostWithJson(user);
        System.out.println(result1);
        System.out.println("------");
        System.out.println(result2);
        System.out.println("------");
        System.out.println(result3);
    }

}
