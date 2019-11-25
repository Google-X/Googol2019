package Console;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoadData {

    private String name;
    private int numOfSearch;
    
    // LIST OF FILE PATH
    private File dataDirectory = new File(System.getProperty("user.home") + "\\Desktop\\Googol");
    
    private Scanner s = new Scanner(System.in);
    
    public LoadData() {

    }
    
    public LoadData(String name){
        this.name = name.toUpperCase();
    }
    
    
    public void Load() {

        String name = s.nextLine();
        name = name.toUpperCase();
        File dataPath = new File(dataDirectory + "\\" + name + "_Data.dat");
        File userHistory = new File(dataDirectory + "\\" + name + "_History.txt");

        try {
            
            if (!dataPath.exists()) {
                this.name = name;
                dataPath = new File(dataDirectory + "\\" + this.name + "_Data.dat");
                userHistory = new File(dataDirectory + "\\" + this.name + "_History.txt");

                System.out.println("Welcome to Googol, " + this.name + "!");

                ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(dataPath));
                PrintWriter p = new PrintWriter(new FileOutputStream(userHistory));
                o.writeUTF(this.name);
                o.writeInt(0);
                o.close();
                p.println("History");
                p.close();

            } else {
                this.name = name;
                dataPath = new File(dataDirectory + "\\" + this.name + "_Data.dat");
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataPath));
                String getName = "";
                int numOfSearch = 0;

                try {
                    while (true) {
                        getName = in.readUTF();
                        numOfSearch = in.readInt();
                    }
                    
                } catch (EOFException EOF) {

                }
                
                this.name = getName;
                this.numOfSearch = numOfSearch;
                System.out.println("Welcome back " + getName + "!");

                in.close();

            }
        } catch (FileNotFoundException FNF) {

        } catch (IOException IO) {
            System.err.println("Error with file output");
        }

    }
    
    public String getName(){
        return name;
    }
    
    public int getNumOfSearch(){
        return numOfSearch;
    }

}
