package Console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class Command {

    private static Scanner s = new Scanner(System.in);
    private String cmd;
    private String name;
    private int numOfSearch;

    // ARRAY FOR SERACHES
    private String dateAndTime[] = {"date", "time", "today"};

    // LIST OF COMMANDS
    private String googolCMD[] = {"g /update", "g /history -v", "g /history -d" , ""};
    private String commandList[] = {"g /update\tUpdate Googol to the latest version",
        "g /history -v\tView list of searches you made in Googol",
        "g /history -d\tDelete searches in Googol",
        "exit\t\tLog out"};

    // LIST OF FILE PATH
    private File dataDirectory = new File(System.getProperty("user.home") + "\\Desktop\\Googol");
    private File dataPath = new File(dataDirectory + "\\" + this.name + "_Data.dat");
    private File userHistory = new File(dataDirectory + "\\" + this.name + "_History.txt");

    public Command() {
        name = "";
        numOfSearch = 0;
    }

    public Command(String name, int numOfSearch) {
        this.name = name;
        this.numOfSearch = numOfSearch;
    }


    public int getNumOfSearch() {
        return numOfSearch;
    }

    public void setNumOfSearch(int numOfSearch) {
        this.numOfSearch = numOfSearch;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    // CONSOLE HERE CONSOLE HERE CONSOLE HERE CONSOLE HERE CONSOLE HERE CONSOLE HERE CONSOLE HERE CONSOLE HERE CONSOLE
    public void cmd() {

        System.out.println("Type help to display a list of command");
        boolean run = true;

        while (run) {

            System.out.print(">>> ");
            cmd = s.nextLine();

            // Prior for Google CMD
            if (cmd.substring(0, 3).equals("g /")) {

                int cmdIndex = 0;

                for (int i = 0; i < googolCMD.length; i++) {

                    if (cmd.equals(googolCMD[i])) {
                        cmdIndex = i;
                        cmdHelp(cmdIndex);
                        break;
                    } 
                    if (i == googolCMD.length - 1){
                        cmdIndex = i;
                        cmdHelp(cmdIndex);
                        break;
                    }
                }
            
            cmd = "";

            } else if (cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("exit")) {
                
                try {

                    dataPath = new File(dataDirectory + "\\" + this.name + "_Data.dat");
                    ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(dataPath));
                    o.writeUTF(this.name);
                    o.writeInt(this.numOfSearch);
                    o.close();

                } catch (IOException IOE) {
                    System.err.println("Problem saving data.");
                }

                run = false;
                System.out.println("Logged out successfully.");
                System.exit(0);
                break;

            } else if (cmd.equalsIgnoreCase("help")) {

                for (int i = 0; i < commandList.length; i++) {
                    System.out.println(commandList[i]);
                }

            } else {

                this.numOfSearch++;

                try {

                    userHistory = new File(dataDirectory + "\\" + this.name + "_History.txt");
                    PrintWriter p = new PrintWriter(new FileOutputStream(userHistory, true));
                    Date currentDate = new Date();
                    p.println("[" + currentDate + "] - " + cmd);
                    p.close();

                } catch (IOException IOE) {
                    System.err.println("Problem saving history.");
                }
            }

            //IF STATEMENTS : DATE AND TIME
            for (int i = 0; i < dateAndTime.length; i++) {
                if (cmd.toLowerCase().contains(dateAndTime[i])) {
                    displayTime();
                    cmd = "";
                    break;
                }
            }

            //IF STATEMENTS
            //IF STATEMENTS
            //IF STATEMENTS
            //IF STATEMENTS
            //IF STATEMENTS
        }
    }   // End of Console

    public void cmdHelp(int cmdIndex) {

        switch (cmdIndex) {

            // Googol update
            case 0:
                System.out.println("Updating...");
                // UPDATE
                Date t = new Date();
                System.out.println("Updated at " + t);
                break;

            // User history
            case 1:
                try {
                    userHistory = new File(dataDirectory + "\\" + this.name + "_History.txt");
                    Scanner s = new Scanner(new FileInputStream(userHistory));

                    while (s.hasNextLine()) {
                        System.out.println(s.nextLine());
                    }

                    s.close();
                } catch (FileNotFoundException FNF) {
                    System.err.println("History not found.");
                }

                break;

            // User requests to remove history
            case 2:
                userHistory = new File(dataDirectory + "\\" + this.name + "_History.txt");
                Scanner s = new Scanner(System.in);

                System.out.print("Are you sure you want to remove history? (Y/N): ");

                char choice = s.nextLine().charAt(0);

                if (choice == 'Y' || choice == 'y') {
                    if (userHistory.delete()) {
                        System.out.println("History is removed.");
                    }
                } else {
                    if (userHistory.exists()) {
                        System.out.println("Action cancelled.");
                    }
                }
                break;

            default:
                System.out.println("'" + cmd + "'" + " is not recognized as a command. Type help to display a list of command.");
        }

    }

    // LIST OF FUNCTIONS LIST OF FUNCTIONS LIST OF FUNCTIONS LIST OF FUNCTIONS LIST OF FUNCTIONS LIST OF FUNCTIONS
    public void displayTime() {
        Date t = new Date();
        System.out.println(t);
    }

}
