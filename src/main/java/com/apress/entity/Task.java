package com.apress.entity;

public class Task {
    private final String name;
    private State state;

    private static int counter;
    private final int id = counter++;

    public Task(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public Task(String name) {
        this.name = name;
        this.state = State.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public void setState(State state) {
        this.state = state;
    }
}
