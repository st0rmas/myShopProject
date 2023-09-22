package Goods;

import Constants.MyStorage;
import Goods.CategoryClass.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class GoodsMenu {
    private ArrayList<Category> categories;


    public GoodsMenu(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void start(){
        int counter = 1;
        for (Category category : categories){
            System.out.println(counter++ + ". "+ category);
        }
        int point = 0;
        Scanner scanner = new Scanner(System.in);
        try{
            point = scanner.nextInt();
            MyStorage.categoriesList.get(point-1).showGoods();
        }catch(Exception ex){
            ex.printStackTrace();
            //System.out.println("You need to enter a number");
        }
    }
    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
