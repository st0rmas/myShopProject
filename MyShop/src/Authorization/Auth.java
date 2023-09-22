package Authorization;
import AdminPanel.AdminPanel;
import Entities.User;
import Constants.MyStorage;
import Menu.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Auth {

    public static User currentUser = new User(null,null);

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        Auth.currentUser = currentUser;
    }

    public void startAuthorization() throws IOException {
        getUserInfo();

        if (userExists()){
            if (currentUser.getLogin().compareTo("admin")==0 && currentUser.getPassword().compareTo("admin")==0){
                AdminPanel panel = new AdminPanel();
                panel.start();
            }
            else{
                Menu menu = new Menu();
                Menu.menuChoose();
            }

        }
        else{
            Registration reg = new Registration(this);
            reg.startRegistration();
        }
    }
    private boolean userExists(){
        for (User user : MyStorage.arrayList){
            if (currentUser.getLogin().compareTo(user.getLogin())==0){
                currentUser.setBalance(user.getBalance());
                currentUser.setId(user.getId());
                return true;
            }
        }
        return false;
    }
    private void getUserInfo(){
        System.out.println("AUTHORIZATION");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Login: ");
        String login = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        currentUser.setLogin(login);
        currentUser.setPassword(password);

    }
}
