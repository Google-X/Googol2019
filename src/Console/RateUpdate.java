package Console;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
Colloborate with github pro @Jackmin801 
*/

public class RateUpdate {

    public RateUpdate(){
        load("exchange.ser");
//        update("exchange.ser");
    }

    public HashMap<String, Double> update(String filename) {
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
                    System.out.println("Cannot find exchange rate for " + currency);
                }
            }

            int count = 0;
            for (String i : out.keySet()) {
                System.out.println(i + ": " + out.get(i));
                if (count > 10) {
                    break;
                }
                count++;
            }
            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));
                writer.writeObject(out);
                writer.close();
                System.out.println("Data is saved in " + filename);
            } catch (Exception i) {
                // Print error
                i.printStackTrace();
            }

            in.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public HashMap<String, Double> load(String filename) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            HashMap<String, Double> exchangeRate = (HashMap<String, Double>) in.readObject();

            // Success message
            System.out.println("Loaded " + filename);
            int count = 0;
            for (String i : exchangeRate.keySet()) {
                System.out.println(i + ": " + exchangeRate.get(i));
                if (count > 10) {
                    break;
                }
                count++;
            }

            in.close();
            return exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
