package Console;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class TellJokes {
    
    private String[] praise = {"Good guess!", "Niceeee", "IQ like 100000", "aMAZiNgggggg", "GENIUS", "Albert Einstein", "WOW! Where do you get your brain from?"};
    
    public TellJokes() {
        
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        
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
                    String guessJoke = sc.nextLine();
                    String answer = s.nextLine();
                    
                    if(answer.toLowerCase().contains(guessJoke.toLowerCase())){
                        System.out.println(praise[r.nextInt(praise.length)]);
                    }
                    
                    System.out.println(answer);
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

}
