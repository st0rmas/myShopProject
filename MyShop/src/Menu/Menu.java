package Menu;

import Authorization.Auth;
import Basket.Basket;
import Constants.MyStorage;
import Entities.User;
import FilesAndStorage.FileClasses.WriteUser;
import Goods.Good;
import Goods.GoodsMenu;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    private String menuName;


    public static void showUserMenu(){
        System.out.println("My Shop");
        System.out.print("1. Goods\n" + "2. Basket\n" + "3. Profile\n" + "0. Exit\n" + ">>> " );
    }

    public static void menuChoose() throws IOException {
        showUserMenu();
        int answer = 0;
        Scanner scanner = new Scanner(System.in);
        try{
            answer = scanner.nextInt();
        } catch (Exception ex){
            System.out.println("You need to enter a number");
        }


        switch (answer){
            case 0:
                break;
            case 1:
                MyStorage.initCategories();
                GoodsMenu goodsMenu = new GoodsMenu(MyStorage.categoriesList);
                goodsMenu.start();
                break;
            case 2:
                updateBasket();
                break;
            case 3:
                System.out.println("My profile");
                System.out.println("Name: ");
                User user = Auth.currentUser;
                System.out.println("Login: " + user.getLogin());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Balance: " + user.getBalance());

                System.out.println("[1]. Change password\n[2].Log out\n[0].Go back");
                int choose;
                choose = scanner.nextInt();
                switch (choose){
                    case 1:
                        String newPassword ="";
                        String temp = "";
                        System.out.print("Enter current password: ");



                        do {
                            temp = scanner.next();
                            if (temp.compareTo(user.getPassword())==0){
                                System.out.print("Enter new password: ");
                                newPassword = scanner.next();
                                Auth.currentUser.setPassword(newPassword);
                            }
                            else{
                                System.out.print("You entered incorrect password, try again: ");
                            }
                        }while (temp.compareTo(user.getPassword())!=0);
                        WriteUser.write(Auth.currentUser);
                        break;
                    case 2:
                        Auth auth = new Auth();
                        auth.startAuthorization();
                        break;
                    case 0:
                        Menu.menuChoose();
                        break;
                    default:
                        System.out.println("Invalid value");
                        break;
                }
                break;
            default:
                System.out.println("Invalid point");
                break;
        }



    }
    public static void updateBasket() throws IOException {
        System.out.println("MY BASKET");
        if (Auth.currentUser.getBasket().getBasketGoods().isEmpty()){
            System.out.println("You need to add good to basket");
            menuChoose();
        }
        else{

            HashMap<Good, Integer> basketGoods = Auth.currentUser.getBasket().getBasketGoods();
            AtomicInteger counter = new AtomicInteger(1);
            basketGoods.forEach((k, v) -> System.out.println(counter.getAndIncrement() +". "+ k + " - " + v));
            System.out.println("Common price - " + Auth.currentUser.getBasket().getPrice() + "$");

            Basket basket = new Basket();
            basket.basketMenu();
        }
    }

}
