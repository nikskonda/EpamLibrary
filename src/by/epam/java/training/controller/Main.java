package by.epam.java.training.controller;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.NewsCover;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.util.EncriptionMD5;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        SignInForm au = new SignInForm("admin","admin");
//
//        UserService us = new UserServiceImpl();
//
//        System.out.println(us.isExistUser(au));

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

//        SignUpForm regForm = new SignUpForm();
//        regForm.setLogin("FromBelarus");
//        regForm.setPassword("password");
//        regForm.setConfirmPassword("password");
//        regForm.setEmail("by@mail.ru");
//        regForm.setFirstName("Eugene");
//        regForm.setLastName("Bulbash");
//
//        ActiveUser activeUser =
//                (ServiceFactory.getInstance().getUserService()
//                .addUser(regForm));
//        System.out.println(activeUser.getLogin());

//        SignInForm af = new SignInForm();
//        af.setLogin("admin");
//        af.setPassword("admin1");
//
//        if (ServiceFactory.getInstance().getUserService().isExistUser(af)){
//            ActiveUser auser = ServiceFactory.getInstance().getUserService().getActiveUser(af.getLogin());
//            System.out.println(auser.getId());
//        }

//        System.out.println(Integer.MAX_VALUE+"");
//        System.out.println(EncriptionMD5.encrypt(Integer.MAX_VALUE+""));

//        String password1 = "123456";
//        String password2 = "12345678901234567890";
//        String password3 = "qwerty";
//        String password4 = "qwertyqweqweqweqweqw";
//        String password5 = "1wgy3er52b";
//
//        System.out.println(EncriptionMD5.encrypt(password1));
//        System.out.println((password1).length());
//        System.out.println(EncriptionMD5.encrypt(password1).length());
//
//        System.out.println(EncriptionMD5.encrypt(password2));
//        System.out.println((password2).length());
//        System.out.println(EncriptionMD5.encrypt(password2).length());
//
//        System.out.println(EncriptionMD5.encrypt(password3));
//        System.out.println((password3).length());
//        System.out.println(EncriptionMD5.encrypt(password3).length());
//
//        System.out.println(EncriptionMD5.encrypt(password4));
//        System.out.println((password4).length());
//        System.out.println(EncriptionMD5.encrypt(password4).length());
//
//        System.out.println(EncriptionMD5.encrypt(password5));
//        System.out.println((password5).length());
//        System.out.println(EncriptionMD5.encrypt(password5).length());

//        UserService serv = ServiceFactory.getInstance().getUserService();
//        User us = serv.getUserByLogin("admin");

//        ResourceBundle rb = ResourceBundle.getBundle("resource.db");
//        System.out.println(rb.getString("login"));

//        Book book = DAOFactory.getInstance().getBookDAO().getBook(24, "en");
//        System.out.println(book.getGenresAsString());
//        System.out.println(book.getAuthorsAsString());

//        Date date = new Date();
//        date.setTime((long)720775807);
//        System.out.println(date);
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Long.MAX_VALUE);

        System.out.println("uyt/d4.jpg.jp".matches("^.+\\.jpg$"));
        int i = (int)5.5;
    }
}
