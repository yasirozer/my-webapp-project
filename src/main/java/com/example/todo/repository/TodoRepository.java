package com.example.todo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing todo items.
 * Handles database operations for todos using SQLite.
 */
public class TodoRepository {

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/todo.db";

    /** Constructor that initializes the todos table. */
    public TodoRepository() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS todos ("
                    + "    id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "    task TEXT NOT NULL);";
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all todos from the database.
     *
     * @return list of all todo task strings
     */
    public List<String> getAllTodos() {
        List<String> todos = new ArrayList<>();
        String query = "SELECT task FROM todos";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                todos.add(rs.getString("task"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    /**
     * Adds a new todo task to the database.
     *
     * @param task the task description
     */
    public void addTodo(String task) {
        String insertSQL = "INSERT INTO todos(task) VALUES(?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, task);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a todo item by ID.
     *
     * @param id the ID of the todo to delete
     */
    public void deleteTodoById(int id) {
        String deleteSQL = "DELETE FROM todos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}