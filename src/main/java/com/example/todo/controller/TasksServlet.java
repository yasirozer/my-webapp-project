package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskDAO;
import com.example.todo.database.DatabaseInitializer;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * REST API servlet for managing tasks.
 * Handles GET, POST, PUT, and DELETE operations for task management.
 */
public class TasksServlet extends HttpServlet {
    
    // Initialize database on servlet load
    static {
        DatabaseInitializer.initialize();
    }
    
    private final TaskDAO taskDAO = new TaskDAO();

    private final Gson gson = new Gson();

    /**
     * Handles GET requests to retrieve task(s).
     * GET /api/tasks - returns all tasks
     * GET /api/tasks/{id} - returns specific task by ID
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Task> tasks = taskDAO.getAllTasks();
            resp.getWriter().write(gson.toJson(tasks));
        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Task task = taskDAO.getTaskById(id);
                if (task != null) {
                    resp.getWriter().write(gson.toJson(task));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    /**
     * Handles POST requests to create a new task.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Task task = gson.fromJson(reader, Task.class);
        taskDAO.addTask(task);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    /**
     * Handles PUT requests to update an existing task.
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Task task = gson.fromJson(reader, Task.class);
        taskDAO.updateTask(task);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * Handles DELETE requests to remove a task by ID.
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                taskDAO.deleteTask(id);
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}