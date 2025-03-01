package com.gyst.entity;

import lombok.Getter;

@Getter
public enum Mood {
    HAPPY("Feeling joyful and positive"),
    SAD("Feeling down or melancholic"),
    ANXIOUS("Feeling nervous or uneasy"),
    EXCITED("Feeling thrilled and enthusiastic"),
    CALM("Feeling peaceful and relaxed"),
    ANGRY("Feeling irritated or mad"),
    GRATEFUL("Feeling appreciative and thankful"),
    LONELY("Feeling isolated or alone"),
    MOTIVATED("Feeling driven and inspired"),
    TIRED("Feeling exhausted or drained"),
    STRESSED("Feeling overwhelmed and tense"),
    CONFIDENT("Feeling self-assured and capable"),
    HOPEFUL("Feeling optimistic and expectant"),
    BORED("Feeling uninterested and restless"),
    FRUSTRATED("Feeling annoyed and discouraged"),
    NOSTALGIC("Feeling sentimental about the past"),
    RELIEVED("Feeling reassured and at ease"),
    OVERWHELMED("Feeling burdened and overloaded"),
    INSPIRED("Feeling creatively or emotionally stimulated"),
    CONTENT("Feeling satisfied and at peace");

    private final String description;

    Mood(String description) {
        this.description = description;
    }
}
