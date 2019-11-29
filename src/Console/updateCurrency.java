
package Console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class updateCurrency {
    
    public static void main(String[] args) {
        googleCurrency(updateCurrency());
    }
    
    public static String[] updateCurrency(){
        
        int list = 0;
        String[] curList = new String[1];
        try{
            // Getting num of line
            File f = new File(System.getProperty("user.home") + "\\Desktop\\CurrencyList.txt");
            Scanner s = new Scanner(new FileInputStream(f));
            
            while(s.hasNextLine()){
                s.nextLine();
                list++;
            }
            
            s.close();
            
            // Storing currency name
            curList = new String[list];
            s = new Scanner(new FileInputStream(f));
            
            while(s.hasNextLine()){
                for(int i = 0; i < list; i++){
                    curList[i] = s.nextLine();
                }
            }
            s.close();
            
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        
        return curList;
        
    }
    
    public static void googleCurrency(String[] curList){
        
        File storeCur = new File(System.getProperty("user.home") + "\\Desktop\\CurrencyRate.txt");
        
        try{
            
            PrintWriter p = new PrintWriter(new FileOutputStream(storeCur));
            
            for(int i = 0; i < curList.length; i++){

                URL u = new URL("https://www.google.com/search?q=" + curList[i] + "+to+myr");
                URLConnection cnn = u.openConnection();
                
                // GET THE CONVERSION RATE
                
                p.println(curList[i]);
                
                
            }
            
            p.close();
            
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
}
