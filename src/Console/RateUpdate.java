package Console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
Colloborate with github pro @Jackmin801 
 */
public class RateUpdate {

    private File filename = new File("ExchangeRate.dat");

    public RateUpdate() {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        if (checkConnection()) {
            System.out.println("Last Update: " + sdf.format(filename.lastModified()));
            update();
        } else {
            System.out.println("Last Update: " + sdf.format(filename.lastModified()));
            System.out.println("Please connect to the Internet in order to update.");
        }
    }

    public boolean checkConnection() {

        try {
            Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
            int x = process.waitFor();
            if (x == 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            
        }
        return false;
    }

    public HashMap<String, Double> update() {
        //Outputs Hashmap of conversion rates
        try {
            Scanner in = new Scanner(new FileInputStream("CurrencyList.txt"));
            HashMap<String, Double> out = new HashMap<String, Double>();
            String url;

            while (in.hasNextLine()) {
                String currency = in.nextLine();
                try {
                    //Construct the link
                    url = "https://www.google.com/search?q=" + currency + "+to+MYR";
                    //Get Request
                    Document doc = Jsoup.connect(url).get();
                    //Zone down the attribute
                    String rate = doc.getElementById("knowledge-currency__updatable-data-column").getElementsByTag("div").attr("data-exchange-rate");
                    out.put(currency, Double.valueOf(rate));
                } catch (Exception e) {
//                    System.out.println("Cannot find exchange rate for " + currency);
                }
            }

            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));
                writer.writeObject(out);
                writer.close();
                Date t = new Date();
                System.out.println("Successfully update currency rate at " + t);

            } catch (Exception i) {
                i.printStackTrace();
            }

            in.close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
