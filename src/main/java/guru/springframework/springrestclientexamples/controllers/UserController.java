package guru.springframework.springrestclientexamples.controllers;


import guru.springframework.springrestclientexamples.services.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;

/**
 * Created by jt on 9/22/17.
 */
@Slf4j
@Controller
public class UserController {
    private ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/users")
    public String formPost(Model model, ServerWebExchange serverWebExchange) {
        // Add an attribute to the model, under "users"
        // Get form data
        // Map that based on the limit value
        model.addAttribute("users", apiService.getUsers(serverWebExchange.getFormData().map(data -> new Integer(data.getFirst("limit")))));

        return "userlist";
    }
}