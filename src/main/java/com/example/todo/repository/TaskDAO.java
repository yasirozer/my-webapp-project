package com.example.todo.repository;

import com.example.todo.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Task persistence.
 * Handles all database operations for tasks using SQLite.
 */
public class TaskDAO {

    private static final String DB_URL =
        "jdbc:sqlite:C:\\tomcat\\apache-tomcat-11.0.18\\"
            + "webapps\\my-webapp-project\\WEB-INF\\todo.db";

    /**
     * Retrieves all tasks from the database.
     *
     * @return List of all tasks
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("completed")
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Retrieves a specific task by ID.
     *
     * @param id the task ID
     * @return the Task object or null if not found
     */
    public Task getTaskById(int id) {
        String query = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getBoolean("completed")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds a new task to the database.
     *
     * @param task the Task object to add
     */
    public void addTask(Task task) {
        String query = "INSERT INTO tasks (title, completed) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setBoolean(2, task.isCompleted());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing task in the database.
     *
     * @param task the Task object with updated values
     */
    public void updateTask(Task task) {
        String query = "UPDATE tasks SET title = ?, completed = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setBoolean(2, task.isCompleted());
            pstmt.setInt(3, task.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task from the database by ID.
     *
     * @param id the ID of the task to delete
     */
    public void deleteTask(int id) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}