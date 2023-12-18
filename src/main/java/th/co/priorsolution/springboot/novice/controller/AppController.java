package th.co.priorsolution.springboot.novice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import th.co.priorsolution.springboot.novice.model.HelloRequest;
import th.co.priorsolution.springboot.novice.model.HelloResponse;
import th.co.priorsolution.springboot.novice.service.HelloService;

@Controller
public class AppController {

    private HelloService helloService;

    public AppController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }


}
