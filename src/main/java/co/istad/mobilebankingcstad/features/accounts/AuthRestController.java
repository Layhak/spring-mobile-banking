package co.istad.mobilebankingcstad.features.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthRestController {
    @PostMapping("login")
    public  String login(){
        return "Login Successfully";
    }

    @PostMapping("refresh-token")
    public  String refreshToken(){
       return  "refresh token" ;
    }
    @PostMapping("register")
    public  String register(){
        return  "register user" ;
    }
}
