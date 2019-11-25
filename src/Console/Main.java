
package Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        LoadData loadUser = new LoadData();
        
        System.out.println("Googol by UM");
        System.out.print("Enter your name to sign in: ");
        
        loadUser.Load();
        Command user = new Command(loadUser.getName(), loadUser.getNumOfSearch());
        user.cmd();
        
    }
}
