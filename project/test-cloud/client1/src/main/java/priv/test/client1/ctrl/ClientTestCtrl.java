package priv.test.client1.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import priv.test.client1.conf.Client1Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ClientTestCtrl {

    private final RestTemplate restTemplate;

    @Autowired
    public ClientTestCtrl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String index(){
        System.out.println(Client1Configuration.WEBSOCKET);
        HashMap<String, Object> req = new HashMap<String, Object>() {{
            put("a", "123");
            put("b", 4L);
        }};
        List<String> list = new ArrayList<String>(){{
            add("123 ");
            add("456");
        }};
        System.out.println(restTemplate.getForObject("http://127.0.0.1:8083/",String.class));
        System.out.println(restTemplate.postForObject(
                "http://127.0.0.1:8083/test2",
                req
                ,String.class));
        System.out.println(restTemplate.postForObject(
                "http://127.0.0.1:8083/test3",
                new HashMap<String,Object>(){{
                    put("list",list);
                }},
                String.class
        ));
        return restTemplate.getForObject(
                "http://127.0.0.1:8083/test1?a={a}&b={b}",
                String.class,
                req);
    }
}
