/*
 * Getting the top 100 current movies
 */
package Console;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IMDb_Current {

    private File filename = new File("CurrentMovieTop100.dat");

    public IMDb_Current() {

//        System.out.println("Data provided by IMDb.");
        
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
    
    public void checkUpdatable(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (checkConnection()) {
            System.out.println("Last Update: " + sdf.format(filename.lastModified()));
            update();
        } else {
            System.out.println("Last Update: " + sdf.format(filename.lastModified()));
            System.out.println("Please connect to the Internet in order to update.");
        }
    }
    
    public void update() {
        
        try {
            String url;
            String list[] = new String[100];

            try {
                //Construct the link
                url = "https://www.imdb.com/chart/moviemeter";
                //Get Request
                Document doc = Jsoup.connect(url).get();
                //Zone down the attribute
                Elements movieList = doc.select("td.titleColumn");

                int cnt = 0;
                for (Element title : movieList) {

                    list[cnt] = title.getElementsByTag("a").first().text() + " " + title.getElementsByTag("span").first().text();
                    cnt++;
                }

            } catch (Exception e) {
                System.err.println("Error getting data.");
            }

            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));

                for (int i = 0; i < 100; i++) {
                    writer.writeUTF(list[i]);
                }

                Date t = new Date();
                System.out.println("Successfully update current Top 100 Movie at " + t);
                writer.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayList() {
        
        Scanner s = new Scanner(System.in);
        System.out.print("Number of movies to be listed [MAX = 100]: ");
        int n = s.nextInt();
        
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            try {
                int count = 0;
                String temp;
                while (true) {
                    temp = in.readUTF();
                    System.out.println((count + 1) + ". " + temp);
                    count++;
                    if(count == n) break;
                }
            } catch (EOFException EOF) {

            }
            in.close();
        } catch (FileNotFoundException fnf) {
            System.err.println("Movie list not found. Try updating the list.");
        } catch (IOException ie) {
            System.err.println("Error reading file. Please try again.");
        }

    }
    
    public void getRank(String parseTitle){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            try {
                int count = 0;
                String temp;
                while (true) {
                    
                    temp = in.readUTF();
                    
                    if(temp.toLowerCase().replace("([.,!?:;'\"/-]|\\s)+", "").contains(parseTitle)){
                        System.out.println((count + 1) + ". " + temp);
                    }
                    
                    count++;
                }
            } catch (EOFException EOF) {

            }
            in.close();
        } catch (FileNotFoundException fnf) {
            System.err.println("Movie list not found. Try updating the list.");
        } catch (IOException ie) {
            System.err.println("Error reading file. Please try again.");
        }
    }

}
