package com.gyst;

import com.gyst.goal.domain.Goal;
import com.gyst.goal.domain.GoalStatus;
import com.gyst.goal.domain.GoalType;
import com.gyst.user.domain.User;

public class TestConstants {
    public static final Long USER_ID = 42L;
    public static final String USER_NAME = "Alice";
    public static final String USER_EMAIL = "alice@domain.com";

    public static final Long GOAL_ID = 333L;
    public static final String GOAL_NAME = "finish this app";
    public static final String GOAL_NEW_NAME = "deploy this app";
    public static final String GOAL_DESC = "by the end of the year, I want to finish alpha version";
    public static final String GOAL_NEW_DESC = "by the end of the November, I want to deploy this app with frontend!";
    public static final String GOAL_DEADLINE = "2025-12-31";
    public static final String GOAL_NEW_DEADLINE = "2025-12-31";
    public static final String GOAL_TYPE = "YEAR";
    public static final String GOAL_NEW_TYPE = "QUARTER";
    public static final String GOAL_STATUS = "CREATED";
    public static final String GOAL_NEW_STATUS = "PLANNED";

    public static final Goal BASE_GOAL = Goal.builder()
            .goalId(GOAL_ID)
            .userId(USER_ID)
            .title(GOAL_NAME)
            .description(GOAL_DESC)
            .deadline(GOAL_DEADLINE)
            .type(GoalType.valueOf(GOAL_TYPE))
            .status(GoalStatus.valueOf(GOAL_STATUS))
            .build();

    public static final Goal UPDATED_GOAL = Goal.builder()
            .goalId(GOAL_ID)
            .userId(USER_ID)
            .title(GOAL_NEW_NAME)
            .description(GOAL_NEW_DESC)
            .deadline(GOAL_NEW_DEADLINE)
            .type(GoalType.valueOf(GOAL_NEW_TYPE))
            .status(GoalStatus.valueOf(GOAL_NEW_STATUS))
            .build();

    public static final User USER = User.builder()
            .userId(USER_ID)
            .name(USER_NAME)
            .email(USER_EMAIL)
            .build();
}
