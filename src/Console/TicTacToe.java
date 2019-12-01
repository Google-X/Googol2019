
package Console;

import java.util.Random;
import java.util.Scanner;

// AI() still acting like stupid AI at the last 2 moves

public class TicTacToe {

    private char[] box;
    private int choice;
    private int n;
    private Scanner s = new Scanner(System.in);

    public TicTacToe() {
        box = new char[9];
        n = 0;

        for (int i = 0; i < box.length; i++) {
            box[i] = ' ';
        }

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
            System.out.print("\n\nEnter the box number [0 to Stop game]: ");
            choice = s.nextInt() - 1;

            if (choice == -1) {
                break;
            }

            while (choice < 0 || choice > 8) {
                System.out.print("\nChoose again in the range: ");
                choice = s.nextInt() - 1;
            }

            playerCheckAvailable();
            displayTic();
            if (checkWin('X')) {
                System.out.println("\nYou win!");
                break;
            }
            if (checkWin('C')) {
                System.out.println("\nGame ties!");
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
            if (checkWin('O')) {
                System.out.println("\nComputer wins!");
                break;
            }
        }
    }

    public void playerCheckAvailable() {
        // Checking if the chosen box is available
        while (true) {

            if (box[choice] == ' ') {
                box[choice] = 'X';
                n++;
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
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    public boolean checkWin(char c) {

        int check = 0;


        if (box[0] == c && box[1] == c && box[2] == c) {
            return true;
        } else if (box[3] == c && box[4] == c && box[5] == c) {
            return true;
        } else if (box[6] == c && box[7] == c && box[8] == c) {
            return true;
        } else if (box[0] == c && box[3] == c && box[6] == c) {
            return true;
        } else if (box[1] == c && box[4] == c && box[7] == c) {
            return true;
        } else if (box[2] == c && box[5] == c && box[8] == c) {
            return true;
        } else if (box[0] == c && box[4] == c && box[8] == c) {
            return true;
        } else if (box[2] == c && box[4] == c && box[6] == c) {
            return true;
        } else{

        for (int i = 0; i < 9; i++) {

            if (box[i] != ' ') {
                check++;
            }

            if (check == 9) {
                return true;
            }

        }
        }
        return false;
    }

    public void AI() {
        // PC RANDOM CHOOSE IF N < 2
        Random r = new Random();
        if (n < 2) {
            while(true){
                if (comCheckAvailable(r.nextInt(9))) {
                    break;
                }
            }
        } else {
            while (true) {

                // BOX 0
                if (box[0] == 'O') {

                    if (box[1] == 'O') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[2] == 'O') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[3] == 'O') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'O') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }
                } // BOX 1
                else if (box[1] == 'O') {

                    if (box[0] == 'O') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[2] == 'O') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    } else if (box[7] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }
                } // BOX 2
                else if (box[2] == 'O') {

                    if (box[0] == 'O') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[1] == 'O') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[5] == 'O') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'O') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }
                } // BOX 3
                else if (box[3] == 'O') {

                    if (box[0] == 'O') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'O') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[5] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }

                } // BOX 4
                else if (box[4] == 'O') {

                    if (box[0] == 'O') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'O') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[1] == 'O') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    } else if (box[7] == 'O') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[2] == 'O') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'O') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[3] == 'O') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[5] == 'O') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    }

                } // BOX 5
                else if (box[5] == 'O') {

                    if (box[2] == 'O') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'O') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[3] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    }

                } // BOX 6
                else if (box[6] == 'O') {

                    if (box[0] == 'O') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    } else if (box[3] == 'O') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[2] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[7] == 'O') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'O') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    }
                } // BOX 7
                else if (box[7] == 'O') {

                    if (box[1] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[6] == 'O') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'O') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    }
                } // BOX 8
                else if (box[8] == 'O') {

                    if (box[2] == 'O') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[5] == 'O') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[4] == 'O') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[0] == 'O') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[6] == 'O') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    } else if (box[7] == 'O') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < 9; i++) {
                        if (comCheckAvailable(i)) {
                            break;
                        }
                    }
                }

                // CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER CHECK USER
                // BOX 0
                if (box[0] == 'X') {

                    if (box[1] == 'X') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[2] == 'X') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[3] == 'X') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'X') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }
                } // BOX 1
                else if (box[1] == 'X') {

                    if (box[0] == 'X') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[2] == 'X') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    } else if (box[7] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }
                } // BOX 2
                else if (box[2] == 'X') {

                    if (box[0] == 'X') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[1] == 'X') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[5] == 'X') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'X') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }
                } // BOX 3
                else if (box[3] == 'X') {

                    if (box[0] == 'X') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'X') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[5] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    }

                } // BOX 4
                else if (box[4] == 'X') {

                    if (box[0] == 'X') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'X') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[1] == 'X') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    } else if (box[7] == 'X') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[2] == 'X') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    } else if (box[6] == 'X') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[3] == 'X') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[5] == 'X') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    }

                } // BOX 5
                else if (box[5] == 'X') {

                    if (box[2] == 'X') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'X') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[3] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    }

                } // BOX 6
                else if (box[6] == 'X') {

                    if (box[0] == 'X') {
                        if (comCheckAvailable(3)) {
                            break;
                        }
                    } else if (box[3] == 'X') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[2] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[7] == 'X') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'X') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    }
                } // BOX 7
                else if (box[7] == 'X') {

                    if (box[1] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(1)) {
                            break;
                        }
                    } else if (box[6] == 'X') {
                        if (comCheckAvailable(8)) {
                            break;
                        }
                    } else if (box[8] == 'X') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    }
                } // BOX 8
                else if (box[8] == 'X') {

                    if (box[2] == 'X') {
                        if (comCheckAvailable(5)) {
                            break;
                        }
                    } else if (box[5] == 'X') {
                        if (comCheckAvailable(2)) {
                            break;
                        }
                    } else if (box[4] == 'X') {
                        if (comCheckAvailable(0)) {
                            break;
                        }
                    } else if (box[0] == 'X') {
                        if (comCheckAvailable(4)) {
                            break;
                        }
                    } else if (box[6] == 'X') {
                        if (comCheckAvailable(7)) {
                            break;
                        }
                    } else if (box[7] == 'X') {
                        if (comCheckAvailable(6)) {
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < 9; i++) {
                        if (comCheckAvailable(i)) {
                            break;
                        }
                    }
                }
                for (int i = 0; i < 9; i++) {
                        if (comCheckAvailable(i)) {
                            break;
                        }
                    }
                break;
            }
        }
    }
}
