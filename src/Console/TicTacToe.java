package Console;

import java.util.Scanner;

public class TicTacToe {

    private char[] box;
    private int[] boxValue;
    private int p1;
    private int com;
    private int[] p1Array;
    private int[] comArray;
    private int n;
    private int choice;
    private Scanner s = new Scanner(System.in);

    public TicTacToe() {
        box = new char[9];
        boxValue = new int[9];
        p1Array = new int[9];
        comArray = new int[9];

        n = 0;

        for (int i = 0; i < box.length; i++) {
            box[i] = ' ';
        }

        boxValue[0] = 8;
        boxValue[1] = 3;
        boxValue[2] = 4;
        boxValue[3] = 1;
        boxValue[4] = 5;
        boxValue[5] = 9;
        boxValue[6] = 6;
        boxValue[7] = 7;
        boxValue[8] = 2;
        
        instruction();
        run();
        
    }

    public void instruction() {

        System.out.println("TicTacToe Instruction: ");
        System.out.println("Choose the number below to make a placement.\n");

        System.out.println(" 1 | 2 | 3 ");
        System.out.println("---+---+---");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---+---+---");
        System.out.println(" 7 | 8 | 9 ");
        System.out.println("\nLet's Begin!\n");

    }

    public void displayTic() {

        for (int i = 0; i < box.length; i++) {

            switch (i) {
                case 1:
                case 4:
                case 7:
                    System.out.print("| " + box[i] + " |");
                    break;
                case 2:
                case 5:
                    System.out.print(" " + box[i] + " \n");
                    System.out.println("---+---+---");
                    break;
                default:
                    System.out.print(" " + box[i] + " ");
                    break;
            }

        }

    }

    public void run() {

        boolean run = true;

        System.out.print("You start first!");

        while (run) {

            // User
            System.out.print("\n\nEnter the box number: ");
            choice = s.nextInt() - 1;

            if (choice == -1) {
                System.exit(0);
            }

            while (choice < 0 || choice > 8) {
                System.out.print("\nChoose again in the range: ");
                choice = s.nextInt() - 1;
            }

            playerCheckAvailable();
            displayTic();
            if (checkWin()) {
                break;
            }

            // Computer
            System.out.print("\n\nComputer is thinking.");

            try {
                Thread.sleep(500);
                System.out.print(".");
                Thread.sleep(500);
                System.out.print(".\n\n");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AI();
            displayTic();
            if (checkWin()) {
                break;
            }
        }
    }

    public void playerCheckAvailable() {
        // Checking if the chosen box is available
        while (true) {

            if (box[choice] == ' ') {
                box[choice] = 'X';
                p1 += boxValue[choice];
                p1Array[n] = boxValue[choice];
                break;
            } else {
                System.out.print("This box is taken, choose again: ");
                choice = s.nextInt() - 1;
            }
        }
    }

    public boolean comCheckAvailable(int boxNum) {
        // Checking if the chosen box is available
        while (true) {
            if (box[boxNum] == ' ') {
                box[boxNum] = 'O';
                com += boxValue[boxNum];
                comArray[n] = boxValue[boxNum];
                n++;
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    public boolean checkWin() {

        for (int i = 0; i < p1Array.length; i++) {
            for (int j = 1; j < p1Array.length; j++) {
                for (int k = 2; k < p1Array.length; k++) {
                    if (p1Array[i] + p1Array[j] + p1Array[k] == 15) {
                        if (p1Array[2] == 0) {
                            return false;
                        } else {
                            System.out.println("\nYou win!");
                            return true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < comArray.length; i++) {
            for (int j = 1; j < comArray.length; j++) {
                for (int k = 2; k < comArray.length; k++) {
                    if (comArray[i] + comArray[j] + comArray[k] == 15) {
                        if (comArray[2] == 0) {
                            return false;
                        } else {
                            System.out.println("\nComputer wins!");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void AI() {

        int min = 15;
        int pcmin = 15;
        int temp, pctemp;
        
        PCGetUserScore:
        for (int i = 0; i < p1Array.length; i++) {
            temp = 0;
            for (int j = 1; j < p1Array.length; j++) {
                if(p1Array[i] == 0 || p1Array[j] == 0){
                    
                }else{
                    temp = 15 - p1Array[i] - p1Array[j];
                    if (temp < min) {
                        min = temp;
                        break PCGetUserScore;
                    }
                }
            }
        }
        
        PCFindsItsScore:
        for (int i = 0; i < comArray.length; i++) {
            pctemp = 0;
            for (int j = 1; j < comArray.length; j++) {
                if(comArray[i] == 0 || comArray[j] == 0){

                }else{
                    pctemp = 15 - comArray[i] - comArray[j];
                    if (pctemp < pcmin) {
                        pcmin = pctemp;
                        break PCFindsItsScore;
                    }
                }
            }
        }
        
        PCTriesToDefendThenWin:
        for (int i = min; i >= 0; i--) {

            if (i != 0) {
                for (int j = 0; j < 9; j++) {
                    if (boxValue[j] == min) {
                        if (comCheckAvailable(j)) {
                            break PCTriesToDefendThenWin;
                        }
                    }
                }
            } else {
                // PC Tries to win
                for (int k = pcmin; k >= 0; k--) {

                    if (k != 0) {
                        for (int l = 0; l < 9; l++) {
                            if (boxValue[l] == pcmin) {
                                if (comCheckAvailable(l)) {
                                    break PCTriesToDefendThenWin;
                                }
                            }
                        }
                    } else {
                        for (int m = 0; m < 9; m++) {
                            if (comCheckAvailable(m)) {
                                break PCTriesToDefendThenWin;
                            }
                        }
                    }
                }
            }
        }
    }
}
