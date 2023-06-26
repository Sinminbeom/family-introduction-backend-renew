package com.minbeom.familyintroductionbackendrenew.service;

import com.minbeom.familyintroductionbackendrenew.dto.UserDTO;
import com.minbeom.familyintroductionbackendrenew.user.User;
import com.minbeom.familyintroductionbackendrenew.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long join(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getPassword(), userDTO.getEmail());
        System.out.println("user.toString() = " + user.toString());
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
