package com.example.todo.database;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Helper used by servlets/controllers to ensure the embedded
 * SQLite database and expected tables exist before CRUD operations.
 * The controllers call {@link #initialize()} from a static initializer block,
 * so this code runs once when the class is loaded.
 */
public final class DatabaseInitializer {

    private static final String DB_URL =
        "jdbc:sqlite:C:\\tomcat\\apache-tomcat-11.0.18\\"
            + "webapps\\my-webapp-project\\WEB-INF\\todo.db";

    private DatabaseInitializer() {
        // utility class
    }

    /**
     * Initializes the SQLite database and creates necessary tables.
     * Creates 'tasks' and 'todos' tables if they don't exist.
     */
    public static void initialize() {
        // Create parent directories if they don't exist (useful in IDE/CI)
        File dbFile = new File(
            "C:\\tomcat\\apache-tomcat-11.0.18\\"
                + "webapps\\my-webapp-project\\WEB-INF\\todo.db");
        File parent = dbFile.getParentFile();
        
        try {
            // Explicitly load the SQLite driver
            Class.forName("org.sqlite.JDBC");
            
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            // Log initialization attempt
            File logFile = new File(
                "C:\\tomcat\\apache-tomcat-11.0.18\\logs\\db-init.log");
            FileWriter fw = new FileWriter(logFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("[" + new java.util.Date() + "] Initializing database at: "
                + DB_URL);
            
            try (Connection conn = DriverManager.getConnection(DB_URL)) {
                if (conn != null) {
                    pw.println("Database connection successful");
                    try (Statement stmt = conn.createStatement()) {
                        // tasks table used by TaskDAO (servlet)
                        stmt.execute(
                            "CREATE TABLE IF NOT EXISTS tasks ("
                                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + "title TEXT NOT NULL,"
                                + "completed BOOLEAN NOT NULL DEFAULT 0);");
                        pw.println("Tasks table created/verified");

                        // todos table used by TodoRepository (spring controller)
                        stmt.execute(
                            "CREATE TABLE IF NOT EXISTS todos ("
                                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + "task TEXT NOT NULL);");
                        pw.println("Todos table created/verified");
                    }
                }
            } catch (SQLException e) {
                pw.println("SQLException: " + e.getMessage());
                e.printStackTrace(pw);
            }
            pw.close();
        } catch (Exception e) {
            // print to stderr; controllers/servlets don't have logging yet
            e.printStackTrace();
        }
    }
}
