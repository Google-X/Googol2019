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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Command {
    
    private static Scanner s = new Scanner(System.in);
    private static Random r = new Random();
    private String cmd;
    private String name;
    private int numOfSearch;

    // LIST OF COMMANDS // Gonna change it to File io
    private String[] ask = {"Try typing ", "Try asking ", "You can try ", "Give a try using "};
    
    private String greetings[] = {"Good day", "Hello there", "Hi hi", "Selamat sejahtera", "Hi there", "Glad to meet you again"};
    
    private String tryCMD[] = {"Tell me a joke", "Convert 100usd to myr", "x 100 eur to jpy", "123CAD -> SGD", "Play Tic Tac Toe", "What is the time now?", "How's the wheather today?", "Any good movie to watch?", "List current popular movies", "List overall good movies", "List of Star Wars movies"};
    
    private String googolCMD[]
            = {"g /update",
                "g /history -v",
                "g /history -d", ""};

    private String commandList[]
            = {"g /update\t\t\tUpdate Googol to the latest version",
                "g /history -v\t\t\tView list of searches you made in Googol",
                "g /history -d\t\t\tDelete searches in Googol",
                "cls\t\t\t\tClear current screen",
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
        System.out.println("Type help to display a list of command");
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

        boolean run = true;

        while (run) {

            System.out.print(">>> ");
            
            try{
            cmd = s.nextLine();

            // Prior for Google CMD
            
// GREETINGS GREETINGS GREETINGS GREETINGS GREETINGS GREETINGS GREETINGS GREETINGS GREETINGS 
            if(cmd.toLowerCase().contains("hello") || cmd.equalsIgnoreCase("hi")){
                System.out.println(greetings[r.nextInt(greetings.length)] + " " + name + "!");
            }
            
// GOOGOL CMD GOOGOL CMD GOOGOL CMD GOOGOL CMD GOOGOL CMD GOOGOL CMD GOOGOL CMD GOOGOL CMD
            else if (cmd.substring(0, 3).equals("g /")) {

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

            }
            
// EXIT QUIT LOGOUT EXIT QUIT LOGOUT EXIT QUIT LOGOUT EXIT QUIT LOGOUT EXIT QUIT LOGOUT EXIT QUIT 
            else if (cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("exit")) {

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
            }  
            
// HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP HELP 
            else if (cmd.equalsIgnoreCase("help")) {

                for (int i = 0; i < commandList.length; i++) {
                    System.out.println(commandList[i]);
                }
            }
            
// CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS CLS
            else if(cmd.equalsIgnoreCase("cls")){
                
                cls();
            
            }
            
// TIC TAC TOE TIC TAC TOE TIC TAC TOE TIC TAC TOE TIC TAC TOE TIC TAC TOE TIC TAC TOE TIC TAC TOE
            else if (cmd.toLowerCase().contains("tic tac toe") || cmd.toLowerCase().contains("tic") || cmd.toLowerCase().contains("tac")) {

                TicTacToeSmarterAI game = new TicTacToeSmarterAI();
                
            }
            
// RATE CONVERT RATE CONVERT RATE CONVERT RATE CONVERT RATE CONVERT RATE CONVERT RATE CONVERT
            else if (cmd.toLowerCase().contains("x ") || cmd.toLowerCase().contains("convert") || cmd.toLowerCase().contains(" to ") || cmd.contains("->")) {

                RateConvert rate = new RateConvert(cmd);
                
            }
            
// MOVIE LIST MOVIE LIST MOVIE LIST MOVIE LIST MOVIE LIST MOVIE LIST MOVIE LIST MOVIE LIST
            else if(cmd.toLowerCase().contains("movie")){
                
                IMDb transferToIMDb = new IMDb(cmd);
                
            }
            
// TELL JOKES TELL JOKES TELL JOKES TELL JOKES TELL JOKES TELL JOKES TELL JOKES TELL JOKES
            else if (cmd.toLowerCase().contains("joke") || cmd.equalsIgnoreCase("again")) {
                
                TellJokes jokes = new TellJokes();

            }
            
// TIME DATE TIME DATE TIME DATE TIME DATE TIME DATE TIME DATE TIME DATE TIME DATE TIME 
            else if(cmd.toLowerCase().contains("time") || cmd.toLowerCase().contains("date")){
                Date t = new Date();
                System.out.println(t);
            }
            
// OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS OTHERS
            else {

                this.numOfSearch++;
                
                System.out.println(ask[r.nextInt(ask.length)] + "\"" + tryCMD[r.nextInt(tryCMD.length)] + "\"" );
                
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
            
            } catch(Exception e){
                System.out.println(ask[r.nextInt(ask.length)] + "\"" + tryCMD[r.nextInt(tryCMD.length)] + "\"" );
            }
        }
    }   // End of Console

    public void cmdHelp(int cmdIndex) {

        switch (cmdIndex) {

            // Googol update
            case 0:
                // UPDATE
                
                System.out.println("List of updates available");
                System.out.println("1. Currency\n2. Movie raking list");
                int updateChoice = s.nextInt();
                
                if(updateChoice == 1){
                    RateUpdate r = new RateUpdate();                    
                    break;
                }
                else if(updateChoice == 2){
                    IMDb_Overall top250 = new IMDb_Overall();
                    top250.checkUpdatable();
                    IMDb_Current top100 = new IMDb_Current();
                    top100.checkUpdatable();
                    break;
                }
                

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
    public void cls(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException io){
            io.printStackTrace();
        } catch(InterruptedException ex){
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
