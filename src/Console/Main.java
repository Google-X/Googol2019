
package Console;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        
        Command user = new Command();
        
        System.out.println("Googol by UM");
        System.out.print("Enter your name to sign in: ");
        
        user.Load();
        user.cmd();
        
    }
}
