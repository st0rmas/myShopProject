package FilesAndStorage.FileClasses;

import Constants.Constants;
import Entities.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteUser {
    public static void write(User user){
        try{
            File file = new File(Constants.PATH_USERS +"/"+ user.getId() + ".txt");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(String.valueOf(user.getId()));
            bufferedWriter.newLine();
            bufferedWriter.write(user.getLogin());
            bufferedWriter.newLine();
            bufferedWriter.write(user.getPassword());
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(user.getBalance()));
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();

        }catch (IOException ex){
            System.out.println("Can't read file");
        }
    }
}
