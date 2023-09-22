package FilesAndStorage.FileClasses;
import Authorization.Auth;
import Basket.Basket;
import Constants.Constants;
import Goods.Good;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteBasketGoods {
    public static void write(Basket basket){
        File file = new File(Constants.PATH_BASKET_GOODS+ "/"+Auth.currentUser.getId()+".txt");
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Good good : basket.getBasketGoods().keySet()){
                bw.write(good.getName() + " - " + good.getPrice()+ "$" + basket.getBasketGoods().get(good).intValue());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            basket.getBasketGoods().clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
