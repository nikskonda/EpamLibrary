package by.epam.java.training.controller;

import by.epam.java.training.model.AuthorizationUser;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.impl.UserServiceImpl;

import java.util.Date;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        AuthorizationUser au = new AuthorizationUser("admin","admin");
//
//        UserService us = new UserServiceImpl();
//
//        System.out.println(us.isExistLoginAndPassword(au));

        String str = "%u041F%u0440%u0438%u0432%u0435%u0442%20%u043C%u0438%u0440";
        String res = str.replaceAll("%","\\");
        System.out.println(res);
    }
}
