package com.chenmeng.project.service.impl.inner;

import com.chenmeng.cmapicommon.service.InnerUserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class InnerUserInterfaceInfoServiceImplTest {

    @Resource
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    @Test
    public void invokeInterfaceCount() {
        boolean b = innerUserInterfaceInfoService.invokeInterfaceCount(1L, 1L);
        // 断言一下 -- 检查假设条件
        Assertions.assertTrue(b);
    }

    @Test
    void hasInvokeNum() {
        if (!innerUserInterfaceInfoService.hasInvokeNum(1L, 1L)) {
            log.error("当前剩余调用次数为 0");
            return ;
        }
    }
}