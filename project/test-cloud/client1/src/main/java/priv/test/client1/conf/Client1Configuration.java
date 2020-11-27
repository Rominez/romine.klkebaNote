package priv.test.client1.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Client1Configuration {

    public static String WEBSOCKET;

    public Client1Configuration(@Value("${hz.service.websocket}") String websocket){
        WEBSOCKET = websocket;
    }

    @Bean("restTemplate")
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
