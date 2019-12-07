
package Console;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TicTacToeSmarterAI {
    
    private char tictactoe[] = new char[9]; 
    
    public TicTacToeSmarterAI()
    {
        Random r = new Random();
        Scanner s = new Scanner(System.in);
        String choice;
        System.out.println("Singleplayer or Multiplayer? ");
        System.out.print("Enter : ");
        choice = s.nextLine().toLowerCase();
        while(!(choice.contains("single")) && !(choice.contains("multi")))
        {
            System.out.println("Only \"Singleplayer\" or \"Multiplayer\" is accepted!") ;
            System.out.print("Enter : ");
            choice = s.nextLine();
        }
        if(choice.equalsIgnoreCase("Multiplayer") || choice.equalsIgnoreCase("Multi"))
        {
        displayInstruction();
        int position; int j = 1; int counter = 0;
        for(int i = 0 ; i<tictactoe.length ; i++)
        {
                tictactoe[i] = ' ';
        }
        while(true)
        {
            int player = 1;
            System.out.print("Enter position for player " + player + " [Any letter to quit] : ");
            position = s.nextInt();
             while(position>9 || position<=0 )
            {
                System.out.print("Position out of range! Please re-enter : ");
                position = s.nextInt();
            }
            while(tictactoe[position-1]=='X' || tictactoe[position-1]=='O')
            {
                System.out.println("The space has already been taken! ");
                System.out.print("Enter another position : ");
                position = s.nextInt();
            }
            tictactoe[position-1] = 'X';
            counter++;
            cls();
            displayTic();
            
            if(counter == 9)
            {System.out.println("Tie! ");break;}
            if(tictactoe[0] == 'X' && tictactoe[1] == 'X' && tictactoe[2] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[0] == 'X' && tictactoe[3] == 'X' && tictactoe[6] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[1] == 'X' && tictactoe[4] == 'X' && tictactoe[7] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[2] == 'X' && tictactoe[5] == 'X' && tictactoe[8] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[3] == 'X' && tictactoe[4] == 'X' && tictactoe[5] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[6] == 'X' && tictactoe[7] == 'X' && tictactoe[8] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[2] == 'X' && tictactoe[4] == 'X' && tictactoe[6] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[0] == 'X' && tictactoe[4] == 'X' && tictactoe[8] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            player++;
            System.out.print("Enter position for player " + player + " [Any letter to quit] : ");
            counter++;
            position = s.nextInt();
            while(position>9 || position<=0 )
            {
                System.out.print("Position out of range! Please re-enter : ");
                position = s.nextInt();
            }
            while(tictactoe[position-1]=='X' || tictactoe[position-1]=='O')
            {   
                System.out.println();
                System.out.println("The space has already been taken! ");
                System.out.println();
                System.out.print("Enter another position : ");
                position = s.nextInt();
            }
            tictactoe[position-1] = 'O';
            cls();
            displayTic();

            if(tictactoe[0] == 'O' && tictactoe[1] == 'O' && tictactoe[2] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[0] == 'O' && tictactoe[3] == 'O' && tictactoe[6] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[1] == 'O' && tictactoe[4] == 'O' && tictactoe[7] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[2] == 'O' && tictactoe[5] == 'O' && tictactoe[8] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[3] == 'O' && tictactoe[4] == 'O' && tictactoe[5] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[6] == 'O' && tictactoe[7] == 'O' && tictactoe[8] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[2] == 'O' && tictactoe[4] == 'O' && tictactoe[6] == 'O' )
            {System.out.println("Player 2 wins!");break;}
            if(tictactoe[0] == 'O' && tictactoe[4] == 'O' && tictactoe[8] == 'O' )
            {System.out.println("Player 2 wins!");break;}
        }
        }
        else if(choice.equalsIgnoreCase("Singleplayer") || choice.equalsIgnoreCase("Single"))
        {
        boolean playerPotentialWin = false;
        displayInstruction();
        int position; int j = 1; int counter = 0;
        for(int i = 0 ; i<tictactoe.length ; i++)
        {
                tictactoe[i] = ' ';
        }
        while(true)
        {
            System.out.print("Enter position for player 1 [Any letter to quit]: ");
            position = s.nextInt();
            while(position>9 || position<=0 )
            {
                System.out.print("Position out of range! Please re-enter : ");
                position = s.nextInt();
            }
            while(tictactoe[position-1]=='X' || tictactoe[position-1]=='O')
            {
                System.out.println("The space has already been taken! ");
                System.out.print("Enter another position : ");
                position = s.nextInt();
            }
            tictactoe[position-1] = 'X';
            counter++;
            cls();
            displayTic();
            
            if(tictactoe[0] == 'X' && tictactoe[1] == 'X' && tictactoe[2] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[0] == 'X' && tictactoe[3] == 'X' && tictactoe[6] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[1] == 'X' && tictactoe[4] == 'X' && tictactoe[7] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[2] == 'X' && tictactoe[5] == 'X' && tictactoe[8] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[3] == 'X' && tictactoe[4] == 'X' && tictactoe[5] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[6] == 'X' && tictactoe[7] == 'X' && tictactoe[8] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[2] == 'X' && tictactoe[4] == 'X' && tictactoe[6] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(tictactoe[0] == 'X' && tictactoe[4] == 'X' && tictactoe[8] == 'X' )
            {System.out.println("Player 1 wins!");break;}
            if(counter == 9)
            {System.out.println("Tie!");break;}
            
            System.out.print("The Computer is thinking.");
            
            try {
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.print(".\n");
            Thread.sleep(500);
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            
            System.out.println("The Computer has made a choice!");
            System.out.println();
            counter++;
            x:while(playerPotentialWin == false)
            {
                if(tictactoe[0]=='O' && tictactoe[1]=='O' && tictactoe[2]==' ') 
                {playerPotentialWin = true;tictactoe[2] = 'O';break;}
                else if(tictactoe[0]=='O' && tictactoe[2]=='O' && tictactoe[1]==' ') 
                {playerPotentialWin = true;tictactoe[1] = 'O';break;}
                else if(tictactoe[1]=='O' && tictactoe[2]=='O' && tictactoe[0]==' ') 
                {playerPotentialWin = true;tictactoe[0] = 'O';break;}
                else if(tictactoe[3]=='O' && tictactoe[5]=='O' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O';break;}
                else if(tictactoe[3]=='O' && tictactoe[4]=='O' && tictactoe[5]==' ') 
                {playerPotentialWin = true;tictactoe[5] = 'O';break;}
                else if(tictactoe[4]=='O' && tictactoe[5]=='O' && tictactoe[3]==' ') 
                {playerPotentialWin = true;tictactoe[3] = 'O';break;}
                else if(tictactoe[6]=='O' && tictactoe[7]=='O' && tictactoe[8]==' ') 
                {playerPotentialWin = true;tictactoe[8] = 'O';break;}
                else if(tictactoe[6]=='O' && tictactoe[8]=='O' && tictactoe[7]==' ') 
                {playerPotentialWin = true;tictactoe[7] = 'O';break;}
                else if(tictactoe[7]=='O' && tictactoe[8]=='O' && tictactoe[6]==' ') 
                {playerPotentialWin = true;tictactoe[6] = 'O';break;}
                else if(tictactoe[0]=='O' && tictactoe[3]=='O' && tictactoe[6]==' ') 
                {playerPotentialWin = true;tictactoe[6] = 'O';break;}
                else if(tictactoe[0]=='O' && tictactoe[6]=='O' && tictactoe[3]==' ') 
                {playerPotentialWin = true;tictactoe[3] = 'O';break;}
                else if(tictactoe[3]=='O' && tictactoe[6]=='O' && tictactoe[0]==' ') 
                {playerPotentialWin = true;tictactoe[0] = 'O';break;}
                else if(tictactoe[1]=='O' && tictactoe[4]=='O' && tictactoe[7]==' ') 
                {playerPotentialWin = true;tictactoe[7] = 'O';break;}
                else if(tictactoe[1]=='O' && tictactoe[7]=='O' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O';break;}
                else if(tictactoe[4]=='O' && tictactoe[7]=='O' && tictactoe[1]==' ') 
                {playerPotentialWin = true;tictactoe[1] = 'O';break;}
                else if(tictactoe[2]=='O' && tictactoe[5]=='O' && tictactoe[8]==' ') 
                {playerPotentialWin = true;tictactoe[8] = 'O';break;}
                else if(tictactoe[2]=='O' && tictactoe[8]=='O' && tictactoe[5]==' ') 
                {playerPotentialWin = true;tictactoe[5] = 'O';break;}
                else if(tictactoe[5]=='O' && tictactoe[8]=='O' && tictactoe[2]==' ') 
                {playerPotentialWin = true;tictactoe[2] = 'O';break;}
                else if(tictactoe[0]=='O' && tictactoe[4]=='O' && tictactoe[8]==' ') 
                {playerPotentialWin = true;tictactoe[8] = 'O';break;}
                else if(tictactoe[0]=='O' && tictactoe[8]=='O' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O';break;}
                else if(tictactoe[4]=='O' && tictactoe[8]=='O' && tictactoe[0]==' ') 
                {playerPotentialWin = true;tictactoe[0] = 'O';break;}
                else if(tictactoe[2]=='O' && tictactoe[4]=='O' && tictactoe[6]==' ') 
                {playerPotentialWin = true;tictactoe[6] = 'O';break;}
                else if(tictactoe[2]=='O' && tictactoe[6]=='O' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O'; break;}
                else if(tictactoe[4]=='O' && tictactoe[6]=='O' && tictactoe[2]==' ') 
                {playerPotentialWin = true;tictactoe[2] = 'O';break;}
                if(tictactoe[0]=='X' && tictactoe[1]=='X' && tictactoe[2]==' ') 
                {playerPotentialWin = true;tictactoe[2] = 'O';break;}
                else if(tictactoe[0]=='X' && tictactoe[2]=='X' && tictactoe[1]==' ') 
                {playerPotentialWin = true;tictactoe[1] = 'O';break;}
                else if(tictactoe[1]=='X' && tictactoe[2]=='X' && tictactoe[0]==' ') 
                {playerPotentialWin = true;tictactoe[0] = 'O';break;}
                else if(tictactoe[3]=='X' && tictactoe[5]=='X' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O';break;}
                else if(tictactoe[3]=='X' && tictactoe[4]=='X' && tictactoe[5]==' ') 
                {playerPotentialWin = true;tictactoe[5] = 'O';break;}
                else if(tictactoe[4]=='X' && tictactoe[5]=='X' && tictactoe[3]==' ') 
                {playerPotentialWin = true;tictactoe[3] = 'O';break;}
                else if(tictactoe[6]=='X' && tictactoe[7]=='X' && tictactoe[8]==' ') 
                {playerPotentialWin = true;tictactoe[8] = 'O';break;}
                else if(tictactoe[6]=='X' && tictactoe[8]=='X' && tictactoe[7]==' ') 
                {playerPotentialWin = true;tictactoe[7] = 'O';break;}
                else if(tictactoe[7]=='X' && tictactoe[8]=='X' && tictactoe[6]==' ') 
                {playerPotentialWin = true;tictactoe[6] = 'O';break;}
                else if(tictactoe[0]=='X' && tictactoe[3]=='X' && tictactoe[6]==' ') 
                {playerPotentialWin = true;tictactoe[6] = 'O';break;}
                else if(tictactoe[0]=='X' && tictactoe[6]=='X' && tictactoe[3]==' ') 
                {playerPotentialWin = true;tictactoe[3] = 'O';break;}
                else if(tictactoe[3]=='X' && tictactoe[6]=='X' && tictactoe[0]==' ') 
                {playerPotentialWin = true;tictactoe[0] = 'O';break;}
                else if(tictactoe[1]=='X' && tictactoe[4]=='X' && tictactoe[7]==' ') 
                {playerPotentialWin = true;tictactoe[7] = 'O';break;}
                else if(tictactoe[1]=='X' && tictactoe[7]=='X' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O';break;}
                else if(tictactoe[4]=='X' && tictactoe[7]=='X' && tictactoe[1]==' ') 
                {playerPotentialWin = true;tictactoe[1] = 'O';break;}
                else if(tictactoe[2]=='X' && tictactoe[5]=='X' && tictactoe[8]==' ') 
                {playerPotentialWin = true;tictactoe[8] = 'O';break;}
                else if(tictactoe[2]=='X' && tictactoe[8]=='X' && tictactoe[5]==' ') 
                {playerPotentialWin = true;tictactoe[5] = 'O';break;}
                else if(tictactoe[5]=='X' && tictactoe[8]=='X' && tictactoe[2]==' ') 
                {playerPotentialWin = true;tictactoe[2] = 'O';break;}
                else if(tictactoe[0]=='X' && tictactoe[4]=='X' && tictactoe[8]==' ') 
                {playerPotentialWin = true;tictactoe[8] = 'O';break;}
                else if(tictactoe[0]=='X' && tictactoe[8]=='X' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O';break;}
                else if(tictactoe[4]=='X' && tictactoe[8]=='X' && tictactoe[0]==' ') 
                {playerPotentialWin = true;tictactoe[0] = 'O';break;}
                else if(tictactoe[2]=='X' && tictactoe[4]=='X' && tictactoe[6]==' ') 
                {playerPotentialWin = true;tictactoe[6] = 'O';break;}
                else if(tictactoe[2]=='X' && tictactoe[6]=='X' && tictactoe[4]==' ') 
                {playerPotentialWin = true;tictactoe[4] = 'O'; break;}
                else if(tictactoe[4]=='X' && tictactoe[6]=='X' && tictactoe[2]==' ') 
                {playerPotentialWin = true;tictactoe[2] = 'O';break;}
                else 
                {
                while(playerPotentialWin == false)
                {       position = r.nextInt(9);
                        if(tictactoe[position] == ' ')
                        {
                            tictactoe[position] = 'O';
                            break x;
                        }
                }
                }
            }
            playerPotentialWin = false;
            
            cls();
            displayTic();
            
            if(tictactoe[0] == 'O' && tictactoe[1] == 'O' && tictactoe[2] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[0] == 'O' && tictactoe[3] == 'O' && tictactoe[6] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[1] == 'O' && tictactoe[4] == 'O' && tictactoe[7] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[2] == 'O' && tictactoe[5] == 'O' && tictactoe[8] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[3] == 'O' && tictactoe[4] == 'O' && tictactoe[5] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[6] == 'O' && tictactoe[7] == 'O' && tictactoe[8] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[2] == 'O' && tictactoe[4] == 'O' && tictactoe[6] == 'O' )
            {System.out.println("Computer wins !");break;}
            if(tictactoe[0] == 'O' && tictactoe[4] == 'O' && tictactoe[8] == 'O' )
            {System.out.println("Computer wins !");break;}   
        }
        }
}
    public void displayInstruction()
    {
        System.out.println("Welcome to Tic Tac Toe! ");
        System.out.println("These are the positions! ");
        int[] sampleoutput=new int[9];
        int r = 1;
        for(int i = 0 ; i<sampleoutput.length ; i++)
        {
            sampleoutput[i] = i+1;
            
            if(i == 1 || i == 4 || i == 7)
            {System.out.printf("| %d |" , sampleoutput[i]);}
            else if((r)%3==0 && r < 9)
            {System.out.printf(" %d \n", sampleoutput[i]);
             System.out.println("---+---+---");}
            else
            {System.out.printf(" %d ", sampleoutput[i]);}
            r++;
        }
            System.out.println("\n");
    }
    
    public void displayTic() {
        
        for (int i = 0; i < tictactoe.length; i++) {

            switch (i) {
                case 1:
                case 4:
                case 7:
                    System.out.print("| " + tictactoe[i] + " |");
                    break;
                case 2:
                case 5:
                    System.out.print(" " + tictactoe[i] + " \n");
                    System.out.println("---+---+---");
                    break;
                default:
                    System.out.print(" " + tictactoe[i] + " ");
                    break;
            }
        }
        System.out.println("\n");
    }
    
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