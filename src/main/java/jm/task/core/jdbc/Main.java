package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Джими", "Хендрикс", (byte) 27);
        userService.saveUser("Курт", "Кобейн", (byte) 27);
        userService.saveUser("Дэвид", "Гилмор", (byte) 80);
        userService.saveUser("Ангус", "Янг", (byte) 25);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
