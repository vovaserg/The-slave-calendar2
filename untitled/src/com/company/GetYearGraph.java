package com.company;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;


public class GetYearGraph {
    private int month;
    private int dayOfMonth;
    private int day;
    private String smena;
    private int smPrI;
    private String smPrIN;

    public String[] GetMonthGr(int m, int d, String s) {
        //month=m;//Номер месяца(1-12)
        dayOfMonth = m;//Дней в месяце
        day = d;//День Месяца
        smena = s;//Смена в тот день
        String[] smPr = {"12", "4", "8", "B"};//Череда смен
        smPrI = Arrays.asList(smPr).indexOf(smena);//Тут херня кажись хз.  Индекс смены в массиве
        String[] grOnMonth = new String[dayOfMonth];
        for (int i = d; i <= dayOfMonth; i++) {
            grOnMonth[i - 1] = smPr[smPrI];
            if (smPrI == 3)
                smPrI = 0;
            else
                smPrI++;
            //smPrIN=smPr[smPrI];
        }
        // if (smPrI==3)
        //   smPrIN=smPr[0];
        // else
        smPrIN = smPr[smPrI];
        smPrI = Arrays.asList(smPr).indexOf(smena);
        for (int i = d; i > 0; i--) {
            grOnMonth[i - 1] = smPr[smPrI];
            if (smPrI == 0)
                smPrI = 3;
            else
                smPrI--;
        }
        return grOnMonth;
    }

    public void GetYearGr() throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.MONTH, month);

        System.out.println("Month");
        while (true) {
            try {
                month = Integer.parseInt(read.readLine()) - 1;
                while (month < 0 || month > 11) {
                    System.out.println("Month not correct\nMonth");
                    month = Integer.parseInt(read.readLine()) - 1;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not number\nMonth");
            }
        }
        calendar.set(Calendar.MONTH, month);
        dayOfMonth = calendar.getActualMaximum(Calendar.DATE);

        System.out.println("Day");
        while (true) {
            try {
                day = Integer.parseInt(read.readLine());
                while (day < 1 || day > dayOfMonth) {
                    System.out.println("Day not corrected.\nDay");
                    day = Integer.parseInt(read.readLine());
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not number\nDay");
            }
        }

        System.out.println("Smena(\"12\",\"4\",\"8\",\"B\")");
        smena = read.readLine();
        while (!smena.equals("12") && !smena.equals("4") && !smena.equals("8") && !smena.equals("B")) {
            System.out.println("Not correct\nSmena(\"12\",\"4\",\"8\",\"B\")\n");
            smena = read.readLine();
        }
        for (int i = month; i < 12; i++) {
            String[] monthNow = GetMonthGr(dayOfMonth, day, smena);
            for (int j = 1; j <= dayOfMonth; j++)
                System.out.print(j + "-" + monthNow[j - 1] + " ");
            System.out.println("");
            month = i + 1;
            calendar.set(Calendar.MONTH, month);
            dayOfMonth = calendar.getActualMaximum(Calendar.DATE);
            smena = smPrIN;
        }
    }
}