package Accountbook;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class Methods {

    private List<String> splitWord(String str) {

        return Arrays.asList(str.split(" "));

    }   //문자열을 " " 단위로 잘라서 리스트에 넣는 메소드

    private int discrimination(List<String> values) {
        int code = 0;

        try {
            if (values.get(0).contains("Web발신")) {
                if (values.get(1).contains("국민")) {
                    code = 1;
                } else {
                    System.out.println("카드가 아닙니다");
                    code = 0;
                }
            } else if (values.get(0).contains("신한")) {
                code = 2;
            } else if (values.get(0).contains("현대")) {
                code = 3;
            } else {
                System.out.println("카드가 아닙니다");
                code = 0;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e);
            exit(2);
        }
        return code;

    }   //첫번째 문자열을 판별하여 카드사의 종류를 파악하는 메소드. 국민카드 1, 신한카드 2, 현대카드 3을 반환.


    public PurchaseInfo searching(String text) {

        int code = -1;
        String usage = "";
        int amountOfPayment = 0;

        List<String> spiltText = splitWord(text);
        try {
            code = discrimination(spiltText);
            switch (code) {
                case 1:
                    for (int i = 6; i < spiltText.size() - 1; i++) {
                        usage = usage.concat(spiltText.get(i));
                    }
                    amountOfPayment = Integer.parseInt(spiltText.get(5).replaceAll("[^0-9]", ""));
                    break;
                case 2:
                    if (spiltText.get(0).contains("체크")) {
                        amountOfPayment = Integer.parseInt(spiltText.get(3).replaceAll("[^0-9]", ""));
                        for (int i = 4; i < spiltText.size() - 1; i++) {
                            usage = usage.concat(spiltText.get(i));
                        }
                    } else {
                        amountOfPayment = Integer.parseInt(spiltText.get(4).replaceAll("[^0-9]", ""));
                        for (int i = 5; i < spiltText.size() - 1; i++) {
                            usage = usage.concat(spiltText.get(i));
                        }
                    }
                    break;
                case 3:
                    for (int i = 3; i < spiltText.size() - 1; i++) {
                        usage = usage.concat(spiltText.get(i));
                    }
                    amountOfPayment = Integer.parseInt(spiltText.get(2).replaceAll("[^0-9]", ""));
                    break;
                default:
                    System.out.println("카드사 문자가 아님 처리 필요");

            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e);
            exit(2);
        }
        return new PurchaseInfo(code, usage, amountOfPayment);
    }   //잘라진 문자열을 이용해서 카드사, 사용처, 사용금액을 추출해내는 매소드

    public void fileRead(String filename, List<PurchaseInfo> listOfPurchase) {

        filename = AccountBookMain.class.getResource("").getPath() + filename;

        //String totalText = "";

        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String fileContent;

            while ((fileContent = in.readLine()) != null) {

                listOfPurchase.add(searching(fileContent));
                //totalText = totalText.concat(fileContent);
            }

            in.close();
        } catch (IOException e) {
            System.err.println(e);
            exit(1);
        }
    }

    public void fileWrite(List<PurchaseInfo> listOfPurchase) {
        int sum = 0;
        int sumKookmin = 0;
        int sumShinhan = 0;
        int sumHyundai = 0;
        int count = 0;
        int countKookmin = 0;
        int countShinhan = 0;
        int countHyundai = 0;

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(AccountBookMain.class.getResource("").getPath().concat("outfile.txt")));
            out.write(String.format("%10s%10s%10s", "은행코드", "사용처", "사용금액\n"));

            for (int i = 0; i < listOfPurchase.size(); i++) {
                PurchaseInfo p = listOfPurchase.get(i);

                sum = sum + p.getAmountOfPayment();
                count++;

                switch( p.getCode() ) {
                    case 1:
                        sumKookmin = sumKookmin + p.getAmountOfPayment();
                        countKookmin++;
                        break;

                    case 2:
                        sumShinhan = sumShinhan + p.getAmountOfPayment();
                        countShinhan++;
                        break;

                    case 3:
                        sumHyundai = sumHyundai + p.getAmountOfPayment();
                        countHyundai++;
                        break;
                    default:
                        break;
                }
                out.write(String.format("%10s%10s%10s\n", p.getCode(), p.getUsage(), p.getAmountOfPayment()));
            }

            out.write("전체 합계: " + sum + ", 전체 평균: " + ((count > 0) ? sum / count : 0) + "\n");
            out.write("국민카드 합계: " + sumKookmin + ", 국민카드 평균: " + ((countKookmin > 0) ? sumKookmin / countKookmin : 0) + "\n");
            out.write("신한카드 합계: " + sumShinhan + ", 신한카드 평균: " + ((countShinhan > 0) ? sumShinhan / countShinhan : 0) + "\n");
            out.write("현대카드 합계: " + sumHyundai + ", 현대카드 평균: " + ((countHyundai > 0) ? sumHyundai / countHyundai : 0) + "\n");
            out.close();
        } catch (IOException e) {
            System.err.println(e);
            exit(1);
        }


    }
}
