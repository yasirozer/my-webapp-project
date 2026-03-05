package com.example.todo.model;

/**
 * Task model class representing a TODO item.
 */
public class Task {
    private int id;
    private String title;
    private boolean completed;

    /** Default constructor. */
    public Task() {}

    /**
     * Constructor with all fields.
     *
     * @param id the task ID
     * @param title the task title
     * @param completed whether the task is completed
     */
    public Task(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    /**
     * Gets the task ID.
     *
     * @return the task ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the task ID.
     *
     * @param id the task ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the task title.
     *
     * @return the task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title.
     *
     * @param title the task title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the task completion status.
     *
     * @param completed true if completed, false otherwise
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}