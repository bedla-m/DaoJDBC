package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql =  "CREATE TABLE IF NOT EXISTS user ("+
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "lastName VARCHAR(100) NOT NULL," +
                "age TINYINT NOT NULL" +
                ")";
        try(Connection connection = Util.getConnection()){
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
            System.out.println("Таблица создана, или уже существует!");
        } catch (SQLException e){
            System.out.println("Ошибка! createUsersTable " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user;";
        try(Connection connection = Util.getConnection()){
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
            System.out.println("Таблица удалена, или её не существует");
        } catch (SQLException e){
            System.out.println("Ошибка! dropUsersTable " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
