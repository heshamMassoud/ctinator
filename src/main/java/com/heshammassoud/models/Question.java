package com.heshammassoud.models;

import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
public class Question {

    private String name;
    private List<String> expressions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private List<Question> questions = new ArrayList<>();


    /**
     * Checks if the current question has an expression that contains the passed {@code message}.
     *
     * @param message the message to compare to the expressions of the current questions.
     * @return true if an expression in the current question contains the passed {@code message}.
     */
    public boolean matches(@Nonnull final String message) {
        return expressions.stream().anyMatch(message::contains);
    }

    /**
     * Finds the first follow up question to a conversation question starter, which could have an expression matching a
     * the passed {@code message}.
     *
     * @param message the message to find if there is a follow up question with an expression that contains it.
     * @return an {@link Optional} containing the matching follow up question.
     */
    @Nonnull
    public Optional<Question> findNext(@Nonnull final String message) {
        return questions.stream()
                        .filter(q -> q.matches(message))
                        .findFirst();
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getName() {
        return name;
    }

    public List<String> getExpressions() {
        return expressions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setName(@Nonnull final String name) {
        this.name = name;
    }

    public void setExpressions(@Nonnull final List<String> expressions) {
        this.expressions = expressions;
    }

    public void setAnswers(@Nonnull final List<String> answers) {
        this.answers = answers;
    }

    public void setQuestions(@Nonnull final List<Question> questions) {
        this.questions = questions;
    }
}
