package Entities;

import Basket.Basket;

public class User {
    private int id;
    private String login;
    private String password;
    private double balance;
    private Basket basket = new Basket();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User(int id, String login, String password, double balance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.basket = new Basket();
    }
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return id + " " + login + " " + password + " " + balance + "$";
    }
}
