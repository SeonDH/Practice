package Accountbook;

import java.util.ArrayList;
import java.util.List;

public class AccountBookMain {

    public static void main(String[] args) {

        List<PurchaseInfo> listOfPurchase = new ArrayList<>();

        Methods methods = new Methods();

        String filename = "file.txt";

        methods.fileRead(filename, listOfPurchase);

        methods.fileWrite(listOfPurchase);


    }
}
