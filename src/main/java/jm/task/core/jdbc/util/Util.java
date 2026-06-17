package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.setProperty;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "KataAcademy";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();

        configuration
        .setProperty("hibernate.connection.driver_class", "org.mySql.Driver")
        .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/user")
        .setProperty("hibernate.connection.username", "root")
        .setProperty("hibernate.connection.password", "KataAcademy")
        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
        .addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
