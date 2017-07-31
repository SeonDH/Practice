package Accountbook;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Methods {

    public int[] sumAverage(List<PurchaseInfo> listp) {

        int n[] = {0, 0};
        int count = 0;

        for (PurchaseInfo p : listp) {
            n[0] = n[0] + p.getAmountOfPayment();
            count++;
        }
        n[1] = (count > 0) ? n[0] / count : 0;

        return n;

    }

    public int[] sumAverage(List<PurchaseInfo> listp, int code) {
        int n[] = {0, 0};
        int count = 0;

        for (PurchaseInfo p : listp) {
            if (p.getCode() == code) {
                n[0] = n[0] + p.getAmountOfPayment();
                count++;
            }
        }
        n[1] = (count > 0) ? n[0] / count : 0;

        return n;

    }

    public ArrayList<CardInfo> getCardInfo() {

        ArrayList<CardInfo> cardInfo = new ArrayList<>();
        String filename = AccountBookMain.class.getResource("").getPath() + "cardInfo.txt";


        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String fileContent;

            while ((fileContent = in.readLine()) != null) {

                String[] split = fileContent.split(" ");
                CardInfo temp = new CardInfo();
                temp.setCardcheck(split[0]);
                temp.setMoneyLocation(Integer.valueOf(split[1]));
                temp.setUsageLocationS(Integer.valueOf(split[2]));
                temp.setUsageLocationF(Integer.valueOf(split[3]));
                temp.setCode(Integer.valueOf(split[4]));
                cardInfo.add(temp);
            }

            in.close();
        } catch (IOException e) {
            System.err.println(e);
            exit(1);
        }
        return cardInfo;
    }

    private List<String> splitWord(String str) {

        return Arrays.asList(str.split(" "));

    }   //문자열을 " " 단위로 잘라서 리스트에 넣는 메소드

    private PurchaseInfo distinguish(String text) {

        ArrayList<CardInfo> test = getCardInfo();

        int code = 0;
        String usage = "";
        int amountOfPayment = 0;

        List<String> splitText = splitWord(text); //todo list로 사용하는게 더 좋은가?

        for (CardInfo a : test) {
            if (text.contains(a.getCardcheck())) {
                code = a.getCode();
                amountOfPayment = Integer.parseInt(splitText.get(a.getMoneyLocation()).replaceAll("[^0-9]", ""));
                for (int i = a.getUsageLocationS(); i < splitText.size() - a.getUsageLocationF(); i++) {
                    usage = usage.concat(splitText.get(i));
                }
            }
        }
        return new PurchaseInfo(code, usage, amountOfPayment);
    }

    public void fileRead(String filename, List<PurchaseInfo> listOfPurchase) {

        filename = AccountBookMain.class.getResource("").getPath() + filename;


        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String fileContent;

            while ((fileContent = in.readLine()) != null) {

                listOfPurchase.add(distinguish(fileContent));
            }

            in.close();
        } catch (IOException e) {
            System.err.println(e);
            exit(1);
        }
    }

    public void fileWrite(List<PurchaseInfo> listOfPurchase) {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(AccountBookMain.class.getResource("").getPath().concat("outfile.txt")));
            out.write(String.format("%10s%10s%10s", "은행코드", "사용처", "사용금액\n"));

            for (PurchaseInfo p : listOfPurchase) {

                out.write(String.format("%10s%10s%10s\n", p.getCode(), p.getUsage(), p.getAmountOfPayment()));

//                if (map.containsKey(p.getCode())) {
//                    map.put(p.getCode(), map.get(p.getCode()).countAndAdd(p.getAmountOfPayment()));
//                } else {
//                    map.put(p.getCode(), new sumAverageOfEachBank(p.getAmountOfPayment(), 1));
//                }


            }

            out.write("전체 합계: " + sumAverage(listOfPurchase)[0] + ", 전채 평균: " + sumAverage(listOfPurchase)[1] + "\n");

            for (int i = 1; i < 4; i++) { //todo enum 완료되면 map 이용 ??
                out.write(i + "의 합계: " + sumAverage(listOfPurchase, i)[0] + ", " + i + "의 평균: " + sumAverage(listOfPurchase, i)[1] + "\n");
            }//todo 동적 enum 완료전까지 임시로 숫자로 code.


            out.close();
        } catch (IOException e) {
            System.err.println(e);
            exit(1);
        }


    }
}
