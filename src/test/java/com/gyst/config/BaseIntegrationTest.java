package com.gyst.config;

import com.gyst.TestConstants;
import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalRepository;
import com.gyst.user.domain.User;
import com.gyst.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BaseIntegrationTest extends TestContainersConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoalRepository goalRepository;

    protected User createUser() {
        return userRepository.save(TestConstants.USER);
    }

    protected Goal createGoal(Long userId) {
        Goal goalToSave = TestConstants.BASE_GOAL;
        goalToSave.setUserId(userId);
        return goalRepository.save(goalToSave);
    }
}
