package by.epam.java.training.controller;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.AuthorizationForm;
import by.epam.java.training.servise.ServiceFactory;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        AuthorizationForm au = new AuthorizationForm("admin","admin");
//
//        UserService us = new UserServiceImpl();
//
//        System.out.println(us.isExistLoginAndPassword(au));

//        String str = "^[\\w]+@[a-zA-Z]+\\.[a-z]+$";
//        System.out.println("qwe".matches(str));
//        System.out.println("qwe@gmail.com".matches(str));
//        System.out.println("qwe@gmailcom".matches(str));
//        System.out.println("qwe@gmail.".matches(str));
//        System.out.println("qwe@gmail.co1m".matches(str));
//        System.out.println("qwe@gma1il.com".matches(str));
//        System.out.println("qwe@gma_il.com".matches(str));
//        System.out.println("qwe@gmail.com ".matches(str));
//        System.out.println(" qwe@gmail.com".matches(str));
//        System.out.println("qwe @gmail.com".matches(str));
//        System.out.println("qw-e@gmail.com".matches(str));

//        RegistrationForm regForm = new RegistrationForm();
//        regForm.setLogin("admin");
//        regForm.setPassword("admin1");
//        regForm.setConfirmPassword("admin1");
//        regForm.setEmail("admin@admin.admin");
//        regForm.setFirstName("Nikita");
//        regForm.setLastName("Shkonda");
//
//        ActiveUser activeUser =
//                (ServiceFactory.getInstance().getUserService()
//                .addUser(regForm));
//        System.out.println(activeUser.getLogin());

        AuthorizationForm af = new AuthorizationForm();
        af.setLogin("admin");
        af.setPassword("admin1");

        if (ServiceFactory.getInstance().getUserService().isExistLoginAndPassword(af)){
            ActiveUser auser = ServiceFactory.getInstance().getUserService().getActiveUser(af.getLogin());
            System.out.println(auser.getId());
        }
    }
}
