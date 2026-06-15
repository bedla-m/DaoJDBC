package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "lastName VARCHAR(100) NOT NULL," +
                "age TINYINT NOT NULL" +
                ")";
        try (Connection connection = Util.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Таблица создана, или уже существует!");
        } catch (SQLException e) {
            System.out.println("Ошибка! createUsersTable " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user;";
        try (Connection connection = Util.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
            System.out.println("Таблица удалена, или её не существует");
        } catch (SQLException e) {
            System.out.println("Ошибка! dropUsersTable " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("User с именем — " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка saveUser" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка saveUser " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM user";
        try (Connection connection = Util.getConnection();
             Statement ps = connection.createStatement();
             ResultSet resultSet = ps.executeQuery(sql)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);

                User user = new User(name, lastName, age);
                user.setId(id);
                listUser.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка в getAllUsers" + e.getMessage());
        }
        return listUser;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user";
        try (Connection connection = Util.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate(sql);
            System.out.println("Данные в таблице удалены!");
        } catch (SQLException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }
}
