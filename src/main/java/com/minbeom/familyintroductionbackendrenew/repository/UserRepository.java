package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
}
