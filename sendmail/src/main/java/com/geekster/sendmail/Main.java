package org.geekster;

public class Main {
    public static void main(String[] args) {

        System.out.println("This application is supposed to send mail.");

        HandleMail mailer = new HandleMail();

        mailer.sendMail();
    }
}