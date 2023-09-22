package Goods.CategoryClass;

import Authorization.Auth;
import Constants.MyStorage;
import FilesAndStorage.FileClasses.ReadGood;
import Goods.Good;
import Goods.GoodsMenu;
import Menu.Menu;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Category {
    private String categoryName;
    private ArrayList<Good> goods;

    public Category(String categoryName) throws IOException {
        this.categoryName = categoryName;
        this.goods = ReadGood.getGoods(categoryName);
    }

    public void showGoods(){
        System.out.println("If you want to exit, enter a 0");
        int counter = 1;
        for (Good good : goods){
            System.out.println(counter++ + ". " + good);
        }
        startBuy();
    }
    public void startBuy(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
       // showGoods();

        System.out.print("Enter a product you want to buy: ");
        try {
            do {
                choice = scanner.nextInt();

                if (choice == 0) {
                    break;

                }

                HashMap<Good, Integer> myBasket = Auth.currentUser.getBasket().getBasketGoods();
                ArrayList<Good> goodsList = new ArrayList<>(myBasket.keySet());
                Good buyGood = goods.get(choice-1);
                ArrayList<Integer> ids = new ArrayList<>();
                for (Good good : goodsList){
                    ids.add(good.getId());
                }
//                for (Good good : myBasket.keySet()){
//                    if (good.hashCode()==buyGood.hashCode()){
//
//                    }
//                }
                if (myBasket.containsKey(buyGood)){
                    int counter = myBasket.get(buyGood).intValue();
                    myBasket.put(buyGood, counter+1);
                }
                else{
                    ids.add(buyGood.getId());
                    myBasket.put(buyGood, 1);
                }
                Auth.currentUser.getBasket().setPrice(goods.get(choice - 1).getPrice() + Auth.currentUser.getBasket().getPrice());

            } while (choice != 0);
            Menu menu = new Menu();
            Menu.menuChoose();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }
}
