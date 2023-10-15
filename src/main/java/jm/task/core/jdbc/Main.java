package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl impl = new UserServiceImpl();
        impl.createUsersTable();
        impl.saveUser("Petya", "Morozov", (byte) 17);
        impl.saveUser("Semen", "Pavlov", (byte) 17);
        impl.saveUser("Kirill", "Rostovskiy", (byte) 17);
        impl.saveUser("Dmitry", "Sinyavin", (byte) 17);
        System.out.println(impl.getAllUsers());
        impl.cleanUsersTable();
        impl.dropUsersTable();
    }
}