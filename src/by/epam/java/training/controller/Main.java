package by.epam.java.training.controller;

import java.util.Date;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();

        System.out.println(date);

        sleep(3000);

        System.out.println(date);

        Date date2 = new Date();
        System.out.println(date2);

    }
}
