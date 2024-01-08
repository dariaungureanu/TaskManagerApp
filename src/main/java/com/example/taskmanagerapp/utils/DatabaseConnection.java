package com.example.taskmanagerapp.utils;


import com.example.taskmanagerapp.Task;
import com.example.taskmanagerapp.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/task_master_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Pitic2014";

    private static Connection connection=null;

    public static Connection getConnection()  {
        try{

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if(connection!=null) {
               System.out.println("Connection Established");
            }else{
                System.out.println("Connection Failed");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void insertUser(String username, String password) throws SQLException {
        Connection conn = getConnection();
        Statement statement;
        try {
            String query = String.format("insert into users(username,password) values('%s','%s')", username, password);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("New user inserted");
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean getUser(String username, String password) throws SQLException {
        Connection conn = getConnection();
        Statement statement;
        boolean ok = false;
        try{
            String query = String.format("select * from users where username='%s' and password='%s'",username,password);
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                ok = true;
                User user = User.getInstance();
                user.setUserId(Integer.parseInt(resultSet.getString("user_id")));
                user.setUsername(resultSet.getString("username"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ok;
    }

    public void insertTask(String title, String description, LocalDate due_date, Integer time_spent, String priority, Integer userId) throws SQLException {
        Connection conn = getConnection();
        Statement statement;
        try {
            String query = String.format("insert into tasks(title,description, due_date, priority, owner_id, time_spent) values('%s','%s', '%s','%s', '%s', '%s')", title, description, due_date, priority, userId, time_spent);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("New task inserted");
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public ResultSet getTask(){
        Connection conn= getConnection();
        //ObservableList<Task>List= FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet=null;
        try{
            //String username = User.getInstance().getUsername();
            System.out.println("Current username in getTask: " + User.getInstance().getUsername());
            String query = String.format("SELECT t.title, t.description, t.due_date, t.priority, t.time_spent,tg.tag_name,c.comment_text\n" +
                    " FROM tasks t JOIN users u ON u.user_id = t.owner_id\n" +
                    " LEFT JOIN task_tags tt ON t.task_id = tt.task_id\n" +
                    " LEFT JOIN tags tg ON tt.tag_id = tg.tag_id\n" +
                    " LEFT JOIN comments c ON t.task_id = c.task_id WHERE u.username = '%s'", User.getInstance().getUsername());
            System.out.println("Query: " + query);
            statement=conn.createStatement();
            resultSet=statement.executeQuery(query);

        }catch(Exception e){
            System.out.println(e);
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return resultSet;
    }
    public void insertTag(String tag, Integer taskId) {
        Connection conn = getConnection();
        PreparedStatement statement = null;

        try {
                // Insert tag into tags table and retrieve the generated tag_id
                String insertTagQuery = "INSERT INTO tags(tag_name) VALUES(?) RETURNING tag_id";
                statement = conn.prepareStatement(insertTagQuery);
                statement.setString(1, tag);
                ResultSet resultSet = statement.executeQuery();

                int tagId = -1;
                if (resultSet.next()) {
                    tagId = resultSet.getInt("tag_id");
                    System.out.println("New tag inserted with tag_id: " + tagId);
                } else {
                    // Handle the case where the generated key was not retrieved
                    throw new SQLException("Failed to retrieve generated tag_id");
                }

                // Insert into task_tags table using the retrieved tag_id
                String insertTaskTagQuery = "INSERT INTO task_tags(task_id, tag_id) VALUES(?, ?)";
                statement = conn.prepareStatement(insertTaskTagQuery);
                statement.setInt(1, taskId);
                statement.setInt(2, tagId);
                statement.executeUpdate();

                System.out.println("Tag associated with the task");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTaskIdFromTaskName(String taskName) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();

            // Assuming 'tasks' table has a 'title' column representing the task name
            String query = "SELECT task_id FROM tasks WHERE title = ?";
            statement = conn.prepareStatement(query);
            statement.setString(1, taskName);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("task_id");
            } else {
                // Handle the case where the task name is not found
                throw new SQLException("Task not found for the given name: " + taskName);
            }
        } finally {
            // Close resources in a finally block
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        }
    }

    public void insertComment(String comment, int taskId) {
        Connection conn = getConnection();
        PreparedStatement statement=null;
        try {
            String insertCommentQuery = "INSERT INTO comments (task_id, comment_text, comment_date) VALUES (?, ?, CURRENT_DATE)";
            statement = conn.prepareStatement(insertCommentQuery);
            statement.setInt(1, taskId);
            statement.setString(2, comment);

            statement.executeUpdate();
            System.out.println("New comment inserted: " + comment);
        } catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet getRemainTask(){
        Connection conn= getConnection();
        //ObservableList<Task>List= FXCollections.observableArrayList();
        Statement statement;
        ResultSet resultSet=null;
        try{
            String query = String.format("SELECT t.title, t.description, t.due_date, t.priority, t.time_spent,tg.tag_name,c.comment_text\n" +
                    " FROM tasks t JOIN users u ON u.user_id = t.owner_id\n" +
                    " LEFT JOIN task_tags tt ON t.task_id = tt.task_id\n" +
                    " LEFT JOIN tags tg ON tt.tag_id = tg.tag_id\n" +
                    " LEFT JOIN comments c ON t.task_id = c.task_id WHERE u.username = '%s' AND tg.tag_name <> 'done'", User.getInstance().getUsername());
            System.out.println("Query: " + query);
            statement=conn.createStatement();
            resultSet=statement.executeQuery(query);

        }catch(Exception e){
            System.out.println(e);
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return resultSet;
    }

    public void updateTask(String title, String description, LocalDate due_date, Integer time_spent, String priority, Integer userId, String oldTitle) {
        Connection conn = getConnection();
        PreparedStatement statement = null;

        try {
            // Get the task ID for the old title
            int taskId = getTaskIdFromTaskName(oldTitle);

            // Prepare the update statement
            StringBuilder updateTaskQuery = new StringBuilder("UPDATE tasks SET ");
            List<Object> parameters = new ArrayList<>();

            if (title != null) {
                updateTaskQuery.append("title=?, ");
                parameters.add(title);
            }
            if (description != null) {
                updateTaskQuery.append("description=?, ");
                parameters.add(description);
            }
            if (due_date != null) {
                updateTaskQuery.append("due_date=?, ");
                parameters.add(java.sql.Date.valueOf(due_date));
            }
            if (priority != null) {
                updateTaskQuery.append("priority=?, ");
                parameters.add(priority);
            }

            updateTaskQuery.append("owner_id=?, ");
            parameters.add(userId);

            if (time_spent != null) {
                updateTaskQuery.append("time_spent=?, ");
                parameters.add(time_spent);
            }

            // Remove the trailing comma and space
            updateTaskQuery.setLength(updateTaskQuery.length() - 2);

            // Append WHERE clause
            updateTaskQuery.append(" WHERE task_id=?");
            parameters.add(taskId);


            statement = conn.prepareStatement(updateTaskQuery.toString());

            // Set parameters
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }

            // Execute the update
            statement.executeUpdate();

            System.out.println("Task updated successfully");
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCategory(String categoryName) {
        Connection conn = getConnection();
        PreparedStatement statement = null;

        try {
            String insertCategoryQuery = "INSERT INTO categories(category_name) VALUES(?)";
            statement = conn.prepareStatement(insertCategoryQuery);
            statement.setString(1, categoryName);
            statement.executeUpdate();

            System.out.println("Category inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error inserting category: " + e.getMessage());
        }
    }

    public void insertProject(String project) {
        Connection conn = getConnection();
        PreparedStatement statement = null;

        try {
            String insertCategoryQuery = "INSERT INTO projects(project_name) VALUES(?)";
            statement = conn.prepareStatement(insertCategoryQuery);
            statement.setString(1, project);
            statement.executeUpdate();

            System.out.println("Project inserted successfully");
        } catch (SQLException e) {
            System.out.println("Error inserting project: " + e.getMessage());
        }
    }

    public ResultSet getData() {
        Connection conn = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Join the projects and categories tables on the respective columns
            String query = "SELECT p.project_name, c.category_name " +
                    "FROM projects p " +
                    "JOIN categories c ON p.project_id = c.category_id";

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void deleteTask(int taskId) {
        Connection conn = getConnection();
        PreparedStatement statement=null;
        try {
            String deleteTaskTag = "DELETE FROM task_tags WHERE task_id = ?";
            statement = conn.prepareStatement(deleteTaskTag);
            statement.setInt(1, taskId);
            statement.executeUpdate();
            System.out.println("New comment inserted: ");

            String deleteCommentsQuery = "DELETE FROM comments WHERE task_id = ?";
            statement = conn.prepareStatement(deleteCommentsQuery);
            statement.setInt(1, taskId);
            statement.executeUpdate();

            String deleteTaskQuery = "DELETE FROM tasks WHERE task_id = ?";
            statement = conn.prepareStatement(deleteTaskQuery);
            statement.setInt(1, taskId);
            statement.executeUpdate();
            System.out.println("Task and associated tags/comments deleted successfully");
        } catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
