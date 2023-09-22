package FilesAndStorage.FileClasses;

import Constants.Constants;
import Entities.User;
import Goods.Good;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadGood {
    public static ArrayList<Good> getGoods(String categoryName) throws IOException {
        ArrayList<Good> goods = new ArrayList<>();
        File folder = new File(Constants.PATH_GOODS);

        File file = new File(folder.getPath() + "/" +categoryName + ".txt");

        if (!file.exists()){
            file.createNewFile();
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String[] lines;
        while(br.ready()){
            lines = br.readLine().split(" - ");
            Good good = new Good(Integer.parseInt(lines[0]), lines[1], Integer.parseInt(lines[2]), Double.parseDouble(lines[3]));
            goods.add(good);
        }


        return goods;
    }
}
