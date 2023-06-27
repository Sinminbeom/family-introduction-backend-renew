package com.minbeom.familyintroductionbackendrenew.controller;

import com.minbeom.familyintroductionbackendrenew.AppConfig;
import com.minbeom.familyintroductionbackendrenew.dto.UserDTO;
import com.minbeom.familyintroductionbackendrenew.domain.User;
import com.minbeom.familyintroductionbackendrenew.repository.UserRepository;
import com.minbeom.familyintroductionbackendrenew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000") // 허용할 출처를 설정합니다.
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/new")
    @ResponseBody
    public Long create(@RequestBody UserDTO userDTO) {
        Long id = userService.join(userDTO);

//        userRepository.save(user);
//        User user1 = userRepository.findById(user.getId());
//        System.out.println("id = " + user.getId());
//        Assertions.assertThat(user).isEqualTo(user1);
        return id;
    }

    @GetMapping("/minbeom")
    @ResponseBody
    public String minbeom() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRepository userRepository = ac.getBean("userRepository", UserRepository.class);
        User user = new User();
        user.setName("minbeom");

        userRepository.save(user);
//        User user1 = userRepository.findById(user.getId());
        System.out.println("id = " + user.getId());
//        Assertions.assertThat(user).isEqualTo(user1);
        return "minbeom";
    }
}
