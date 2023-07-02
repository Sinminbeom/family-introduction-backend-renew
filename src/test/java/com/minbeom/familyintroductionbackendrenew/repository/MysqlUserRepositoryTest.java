package com.minbeom.familyintroductionbackendrenew.repository;

import com.minbeom.familyintroductionbackendrenew.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
// 테스트 트랜잭션
@Transactional
class MysqlUserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    void save() {
        User user = new User();
        user.setName("min");
        user.setEmail("minbeom@swm.ai");
        user.setPassword("fdfdfd");

        userRepository.save(user);
        System.out.println("user.getId() = " + user.getId());
        User user1 = userRepository.findById(user.getId()).get();

        Assertions.assertThat(user.getName()).isEqualTo(user1.getName());
    }

}