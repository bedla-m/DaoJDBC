package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "lastName VARCHAR(100) NOT NULL, " +
                "age TINYINT NOT NULL" +
                ")";
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tr = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            System.out.println("table create");
        } catch (Exception e){
            System.out.println("Ошибка! ");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tr = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tr.commit();
            System.out.println("Таблица удалена!");
        } catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tr = session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tr = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tr.commit();
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        try(Session session = Util.getSessionFactory().openSession()){
            user = session.createQuery("FROM User",User.class).list();
        } catch (Exception e){
            System.out.println("Ошибка! " + e.getMessage());
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            Transaction tr = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            tr.commit();
        }catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
