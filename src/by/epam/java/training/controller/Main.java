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

        String str = "[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]";
        System.out.println("qwe".matches(str));
        System.out.println("1qwe".matches(str));
        System.out.println("qwe-".matches(str));
        System.out.println("qwe_".matches(str));
        System.out.println("q-e".matches(str));
        System.out.println("q_-e".matches(str));
        System.out.println("-qwe".matches(str));

    }
}
