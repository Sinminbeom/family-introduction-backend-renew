package com.minbeom.familyintroductionbackendrenew.service;

import com.minbeom.familyintroductionbackendrenew.dto.UserDTO;
import com.minbeom.familyintroductionbackendrenew.domain.User;
import com.minbeom.familyintroductionbackendrenew.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(UserDTO userDTO) {
        User user = UserDTO.toUser(userDTO);
        validateDuplicateUser(user);
        userRepository.save(user);
        return user;
    }

    public User login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail()).get();
        if (user.getPassword().equals(userDTO.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
}
