package priv.test.client2.ctrl;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClientTestCtrl {

    @GetMapping("/")
    public String index(){
        System.out.println("hello world");
        return "hello world";
    }

    @GetMapping("/test1")
    public String test1(@RequestParam String a,@RequestParam Long b){
        return a+b;
    }

    @GetMapping("/test2")
    public String test1(@RequestParam Long b,@RequestParam String a){
        return a+b;
    }

    @PostMapping("/test2")
    public String test2(@RequestBody Map<String,Object> map){
        return (String)map.get("a")+map.get("b");
    }

    @PostMapping("/test3")
    public String test3(@RequestBody Map<String,Object> map){
        StringBuilder builder = new StringBuilder();
        List<String> list = (List<String>) map.get("list");
        for (String s : list) {
            builder.append(s);
        }

        return builder.toString();
    }
}
