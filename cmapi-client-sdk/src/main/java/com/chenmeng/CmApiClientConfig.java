package com.chenmeng;

import com.chenmeng.sdk.client.CmApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Data
@ComponentScan
@Configuration
//定义一个配置前缀
@ConfigurationProperties(prefix = "chenmeng.api.client")
public class CmApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public CmApiClient cmApiClient() {
        return new CmApiClient(accessKey, secretKey);
    }

}
