import Authorization.Auth;
import Authorization.Registration;
import FilesAndStorage.FileClasses.ReadUser;
import Menu.Menu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        enter();

    }


    private static void enter() throws IOException {

        int choose = getAuthOrRegPoint();

        do {
            switch (choose) {
                case 1:
                    Auth auth = new Auth();
                    auth.startAuthorization();
                    break;
                case 2:
                    Registration reg = new Registration();
                    reg.startRegistration();
                    break;
                default:
                    System.out.println("Invalid value");
                    break;
            }
        }while (getAuthOrRegPoint()!=0);

    }

    private static int getAuthOrRegPoint(){

        int answer = 0;
        boolean flag;

        do{
            try{
                Scanner scanner = new Scanner(System.in);
                System.out.println("[1]. Authorization\n" +
                        "[2]. Registration\n" +
                        "[0]. Exit ");
                System.out.print(">>> ");
                answer = scanner.nextInt();
                flag = true;
            }catch (InputMismatchException ex){
                flag = false;
                System.out.println("You should enter a NUMBER");

            }
        }while (!flag);
        return answer;

    }




}