package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.AppConfig;
import com.minbeom.familyintroductionbackendrenew.dto.UserDTO;
import com.minbeom.familyintroductionbackendrenew.domain.User;
import com.minbeom.familyintroductionbackendrenew.exception.InvalidParameterException;
import com.minbeom.familyintroductionbackendrenew.repository.UserRepository;
import com.minbeom.familyintroductionbackendrenew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // 허용할 출처를 설정합니다.
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseBody
    public Long create(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        Long id = userService.join(userDTO);

        return id;
    }

    @GetMapping("/minbeom")
    @ResponseBody
    public String select() {
        Long id = userService.join(userDTO);
    }
}
