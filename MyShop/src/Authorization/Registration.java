package Authorization;

import Entities.User;
import FilesAndStorage.FileClasses.WriteUser;
import Constants.MyStorage;

import java.util.Random;
import java.util.Scanner;

import static Authorization.Auth.currentUser;

public class Registration {
    private Auth auth;

    public Registration(){}
    public Registration(Auth auth){
        this.auth = auth;
    }

    public void startRegistration(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("REGISTRATION");
        getUserInfoRegistration();
        MyStorage.showMenu();
    }
    private void getUserInfoRegistration(){
        Scanner scanner = new Scanner(System.in);

        if (auth==null){
            System.out.print("Login: ");
            String login = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            currentUser.setLogin(login);
            currentUser.setPassword(password);
        }
        else{
            currentUser.setPassword(auth.getCurrentUser().getPassword());
            currentUser.setLogin(auth.getCurrentUser().getLogin());
        }
        currentUser.setId(getRandomId());
        currentUser.setBalance(10000);
        WriteUser.write(currentUser);
        System.out.println("Successful registration!");
        System.out.print("Press any button to continue ");
        scanner.next();

    }

    private int getRandomId(){

        Random rnd = new Random();
        int id = rnd.nextInt(1,1000);
        for (User user : MyStorage.arrayList){
            while (id == user.getId()){
                id = rnd.nextInt(1,1000);
            }
        }
        return id;
    }

}