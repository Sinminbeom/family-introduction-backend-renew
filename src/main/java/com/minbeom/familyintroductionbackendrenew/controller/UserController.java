package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.AppConfig;
import com.minbeom.familyintroductionbackendrenew.dto.UserDTO;
import com.minbeom.familyintroductionbackendrenew.domain.User;
import com.minbeom.familyintroductionbackendrenew.exception.InvalidParameterException;
import com.minbeom.familyintroductionbackendrenew.repository.UserRepository;
import com.minbeom.familyintroductionbackendrenew.response.Response;
import com.minbeom.familyintroductionbackendrenew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;

@Controller
@CrossOrigin(origins = "*") // 허용할 출처를 설정합니다.
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        User user = userService.join(userDTO);
        Response response = new Response(200, user);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Response> login(@RequestBody UserDTO userDTO) {
        User user = userService.login(userDTO);
        Response response = new Response(200, user);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("utf-8")));
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
