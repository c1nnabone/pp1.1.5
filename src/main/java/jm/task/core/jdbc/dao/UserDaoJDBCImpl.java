package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Util.Connect().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, age INT NOT NULL);");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Util.Connect().createStatement().executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement state = null;
            state = Util.Connect().prepareStatement("INSERT INTO Users(name, lastname, age) VALUES(?, ?, ?)");
            state.setString(1, name);
            state.setString(2, lastName);
            state.setByte(3, age);
            state.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement rem = null;
            rem = Util.Connect().prepareStatement("DELETE FROM Users WHERE id = ?");
            rem.setLong(1, id);
            rem.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            PreparedStatement all = Util.Connect().prepareStatement("SELECT * FROM Users");
            ResultSet res = all.executeQuery();
            while (res.next()) {
                Long id = res.getLong("id");
                String name = res.getString("name");
                String lastName = res.getString("lastname");
                Byte age = res.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        try {
            Util.Connect().createStatement().executeUpdate("DELETE FROM Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
