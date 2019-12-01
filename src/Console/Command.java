package Console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Command {

    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();
    private String cmd;
    private String name;
    private int numOfSearch;

    // ARRAY FOR SERACHES
    private String dateAndTime[] = {"date", "time", "today"};

    // LIST OF COMMANDS // Gonna change it to File io
    private String googolCMD[]
            = {"g /update",
                "g /history -v",
                "g /history -d", ""};

    private String commandList[]
            = {"g /update\t\t\tUpdate Googol to the latest version",
                "g /history -v\t\t\tView list of searches you made in Googol",
                "g /history -d\t\t\tDelete searches in Googol",
                "Time\t\t\t\tDisplay time & date",
                "Convert 123.12USD to EUR\tConvert currency",
                "Tic Tac Toe\t\t\tPlay Tic Tac Toe",
                "Jokes\t\t\t\tSkrattar du förlorar du",
                "Exit\t\t\t\tLog out"};

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
                    if (i == googolCMD.length - 1) {
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

            } else if (cmd.toLowerCase().contains("tic tac toe") || cmd.toLowerCase().contains("tic") || cmd.toLowerCase().contains("tac")) {

                TicTacToe game = new TicTacToe();

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

            if (cmd.toLowerCase().contains("convert") || cmd.toLowerCase().contains(" to ") || cmd.contains("->")) {

                String CUR1 = "";
                String CUR2 = "";
                double CUR1Rate = 0.0;
                double CUR2Rate = 0.0;
                double rate = 0.0;
                String[] temp = cmd.split(" ");
                
                if(temp.length < 3){
                    System.out.println("Please include two currencies in your sentence. (eg. 1USD -> MYR or 1 EUR to MYR)");
                    continue;
                }
                
                String getAmount = "";
                double amount = 0;

                for (int i = 0; i < cmd.length(); i++) {
                    if (isNumeric(String.valueOf(cmd.charAt(i)))) {
                        getAmount += cmd.charAt(i);
                    } else if (cmd.charAt(i) == '.') {
                        getAmount += ".";
                    }
                }

                amount = Double.parseDouble(getAmount);

                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].equalsIgnoreCase("to") || temp[i].equals("->")) {
                        CUR2 = temp[i + 1].toUpperCase();
                        break;
                    }
                }

                for (int j = temp.length - 1; j >= 0; j--) {
                    if (temp[j].equalsIgnoreCase("to") || temp[j].equals("->")) {
                        CUR1 = temp[j - 1].toUpperCase();

                        if (CUR1.contains(getAmount)) {
                            CUR1 = CUR1.replaceAll(getAmount, "");
                            CUR1 = CUR1.replaceAll(" ", "");
                        }

                        break;
                    }
                }

                RateLoad a = new RateLoad();
                CUR1Rate = a.load(CUR1);

                if (CUR2.equals("MYR")) {

                    System.out.printf("%f %s = %f %s\n", amount, CUR1, (amount * CUR1Rate), CUR2);

                } else {
                    CUR2Rate = a.load(CUR2);

                    if (CUR2Rate == 0) {
                        System.out.println("Sorry! Currency " + CUR2 + " is not found in our database.");
                        continue;
                    }

                    rate = CUR1Rate * (1 / CUR2Rate);
                    double converted = amount * rate;
                    System.out.printf("%f %s = %.3f %s\n", amount, CUR1, converted, CUR2);
                }
            }

            if (cmd.toLowerCase().contains("joke") || cmd.equalsIgnoreCase("again")) {
                try {
                    // Get lines
                    Scanner s = new Scanner(new FileInputStream("Jokes.txt"));
                    int line = 0;

                    while (s.hasNextLine()) {
                        line++;
                        s.nextLine();
                    }

                    s.close();

                    int randomJokesLine = 1 + r.nextInt(line);

                    // Get jokes line
                    while (randomJokesLine % 2 == 0) {
                        randomJokesLine = 1 + r.nextInt(line);

                        if (randomJokesLine % 2 != 0) {
                            break;
                        }
                    }

                    s = new Scanner(new FileInputStream("Jokes.txt"));

                    for (int i = 0; i < line; i++) {

                        if (i == randomJokesLine) {
                            System.out.println(s.nextLine());

                            System.out.print("Give a guess: ");
                            String guessJoke = this.s.nextLine();

                            System.out.println(s.nextLine());
                            break;
                        } else {
                            s.nextLine();
                        }

                    }

                    s.close();

                } catch (FileNotFoundException fnf) {
                    System.out.println("Jokes not found!");
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
                // UPDATE
                RateUpdate r = new RateUpdate();
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

    public boolean isNumeric(String strNum) {

        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
