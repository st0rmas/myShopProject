package Basket;

import Authorization.Auth;
import FilesAndStorage.FileClasses.WriteBasketGoods;
import FilesAndStorage.FileClasses.WriteUser;
import Goods.Good;
import Menu.Menu;
import org.w3c.dom.ranges.RangeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Basket {
    private HashMap<Good, Integer> basketGoods;
    private double price;
    public Basket(){
        basketGoods = new HashMap<>();
//        price = 0;
    }


    private void basketMenuPoints(){
        System.out.print("[1]. Make purchase\n[2]. Delete good\n[0]. Go back\n ");
    }
    private int basketChoice(){
        basketMenuPoints();
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.print(">>>");
            choice = scanner.nextInt();

        }catch (Exception ex){
            System.out.println("You need to enter a number");
        }
        return choice;
    }

    public void basketMenu() throws IOException {
        switch (basketChoice()){
            case 1:
                if (price< Auth.currentUser.getBalance()){
                    WriteBasketGoods.write(Auth.currentUser.getBasket());
                    double newBalance = Auth.currentUser.getBalance()-Auth.currentUser.getBasket().getPrice();
                    Auth.currentUser.setBalance(newBalance);
                    System.out.println(Auth.currentUser);
                    WriteUser.write(Auth.currentUser);
                    System.out.println("Thank you for purchase!");
                    Menu menu = new Menu();
                    try {
                        Menu.menuChoose();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else{
                    System.out.println(Auth.currentUser.getBalance());
                    System.out.println("You don't have enough money on your balance to make purchase");
                }
                break;
            case 2:
                int index;
                System.out.print("Enter a good you want to delete: ");

                try{
                    Scanner scanner = new Scanner(System.in);
                    index = scanner.nextInt();
                    HashMap<Good, Integer> goods = Auth.currentUser.getBasket().getBasketGoods();
                    ArrayList<Good> listGoods = new ArrayList<>(goods.keySet());
                    try{
                        Good goodDelete = listGoods.get(index-1);
                        if (goods.get(goodDelete).intValue()==1){
                            goods.remove(goodDelete);
                        }
                        else{
                            goods.put(goodDelete, goods.get(goodDelete).intValue()-1);
                        }
                    } catch (RangeException ex){
                        System.out.println("Out of range");
                    }
                }catch (ArithmeticException ex){
                    System.out.println("You need to enter a number");
                }
                Menu.updateBasket();
                break;
            case 0:
                Menu.menuChoose();
                break;
            default:
                System.out.println("Invalid value");
                Menu.menuChoose();
                break;
        }
    }
    public Basket(HashMap<Good,Integer> basketGoods) {
        this.basketGoods = basketGoods;
    }

    public HashMap<Good, Integer> getBasketGoods() {
        return basketGoods;
    }

    public void setBasketGoods(HashMap<Good, Integer> basketGoods) {
        this.basketGoods = basketGoods;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
