package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Джими","Хендрикс",(byte)27 );
        userDaoJDBC.saveUser("Курт","Кобейн",(byte)27);
        userDaoJDBC.saveUser("Дэвид", "Гилмор",(byte) 80);
        userDaoJDBC.saveUser("Ангус","Янг",(byte)25);
        System.out.println(userDaoJDBC.getAllUsers().toString());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
