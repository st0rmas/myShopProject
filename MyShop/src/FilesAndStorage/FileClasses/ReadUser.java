package FilesAndStorage.FileClasses;


import Constants.Constants;
import Entities.User;

import java.io.*;
import java.util.ArrayList;

public class ReadUser {
    public static ArrayList<User> getUsers() throws IOException {
        ArrayList<User> users = new ArrayList<>();
        File folder = new File(Constants.PATH_USERS);

        File[] files = folder.listFiles();

        for (File file : files){
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.ready()){
                int id = Integer.parseInt(bufferedReader.readLine());
                String login = bufferedReader.readLine();
                String password = bufferedReader.readLine();
                double balance = Double.parseDouble(bufferedReader.readLine());
                users.add(new User(id,login,password, balance));
            }
            bufferedReader.close();
        }
        return users;
    }
}
