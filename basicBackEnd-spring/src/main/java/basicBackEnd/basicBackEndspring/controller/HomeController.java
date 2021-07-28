package basicBackEnd.basicBackEndspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /*localhost:8080호출시 먼저 템플릿 패키지를 찾기 때문에 index.html보다
    * home.html이 먼저 보여진다. */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
