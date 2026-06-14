package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "KataAcademy";

    public static void connectToDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            if (!connection.isClosed()) {
                System.out.println("Соединение установлено");
            }
            } catch (SQLException e) {
            System.err.println("Ошибка подключения");
        }
    }
}
