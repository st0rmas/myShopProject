package Constants;

import Entities.User;
import FilesAndStorage.FileClasses.ReadGood;
import FilesAndStorage.FileClasses.ReadUser;
import Goods.CategoryClass.Category;
import Goods.Good;
import Goods.Phones.Phone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyStorage {
    public static ArrayList<User> arrayList ;
    public static ArrayList<Good> goodsList;

    static {
        try {
            arrayList = ReadUser.getUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<Category> categoriesList = new ArrayList<>();

    public static void initCategories() throws IOException {
        File folder = new File(Constants.PATH_GOODS);

        File[] files = folder.listFiles();
        categoriesList.clear();
        for (File file : files){
            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            Category category = new Category(fileName);
            categoriesList.add(category);
        }

    }
    public static void getGoods(){
//        File goodsFolder = new File(Constants.PATH_GOODS);
//        File[] categories = goodsFolder.listFiles();
          ArrayList<Good> goods = new ArrayList<>();
        for (Category file : categoriesList){
            int counter = 1;
            for (Good good : file.getGoods()){
                goodsList.add(good);
                System.out.println(counter++ + ". "+ good.getId() + " " + good);
            }

        }
    }
    public static void showMenu(){
        System.out.println("My Shop");
        System.out.print("1. Goods\n" + "2. Basket\n" + "3. Profile\n" + "0. Exit\n" + ">>> " );
    }

}
