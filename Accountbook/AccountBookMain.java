package Accountbook;

import java.util.ArrayList;

public class AccountBookMain {

    public static void main(String[] args) {

        ArrayList<PurchaseInfo> listOfPurchase = new ArrayList();

        Methods methods = new Methods();

        String filename = "file.txt";

        methods.fileRead(filename, listOfPurchase);

        methods.fileWrite(listOfPurchase);


    }
}
