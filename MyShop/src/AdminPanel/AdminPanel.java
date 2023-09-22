package AdminPanel;

import Authorization.Auth;
import Constants.Constants;
import Constants.MyStorage;
import Entities.User;
import FilesAndStorage.FileClasses.WriteUser;
import Goods.Good;
import Goods.GoodsMenu;
import Menu.Menu;

import java.io.File;
import java.util.Scanner;

public class AdminPanel {
    private void showAdminMenu(){
        System.out.print("[1]. Show users\n[2]. Delete user\n[3]. Edit good\n[4]. Edit user\n[0]. Exit\n>>> ");
    }
    public void start(){
        {
            showAdminMenu();
            int answer = 0;
            Scanner scanner = new Scanner(System.in);
            try{
                answer = scanner.nextInt();
            } catch (Exception ex){
                System.out.println("You need to enter a number");
            }


            switch (answer) {
                case 0:
                    break;
                case 1:
                    int counter = 1;
                    for (User user : MyStorage.arrayList){
                        if (user.getLogin().compareTo("admin")!=0){
                            System.out.println(counter++ +". " +  user);
                        }
                    }
                    System.out.println("Enter something");
                    String temp = scanner.next();
                    start();
                    break;
                case 2:
                    deleteUserMenuPoint();
                    start();
                    break;
                case 3:
                    MyStorage.getGoods();
                    break;
                case 4:
                    break;
                default:
                    break;


            }

        }
    }
    private void deleteUserMenuPoint(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an id of user you want to delete: ");
        int id = scanner.nextInt();
        boolean deleted = false;
        for (User user : MyStorage.arrayList){
            if (user.getId()==id){
                File file = new File(Constants.PATH_USERS + "/"+id + ".txt");
                file.delete();
                deleted = true;
            }

        }
        if (!deleted) {
            deleteUserMenuPoint();
        }
    }
    private void editGoodsList(){
        Scanner scanner = new Scanner(System.in);
        int idGood = scanner.nextInt();
        for (Good good : MyStorage.goodsList){
            if (good.getId() == idGood){
                changeGood(good);
            }
            else{
                editGoodsList();
            }
        }
    }
    private void changeGood(Good good){
        System.out.println("1. Name\n2. Price\n3. Rate");
        System.out.print("Enter what you want to change: ");
        Scanner scanner = new Scanner(System.in);
        int changeId = scanner.nextInt();
        switch (changeId){
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.next();
                good.setName(newName);
                break;
            case 2:

                System.out.print("Enter new name: ");
                Double newPrice = scanner.nextDouble();
                good.setPrice(newPrice);


                break;
            case 3:
                System.out.print("Enter new name: ");
                double newRate = scanner.nextDouble();
                good.setRate(newRate);
                break;
            default:
                System.out.println("Invalid point");
                break;
        }

    }

}
