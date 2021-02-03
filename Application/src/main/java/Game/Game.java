package Game;

import ConsoleOutput.ConsoleOutput;
import ConsoleInput.ConsoleInput;

import java.util.Scanner;

public class Game {

    public static Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {

        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();
        //affichage bienvenue
        System.out.println(consoleOutput.welcome());

        //affichage menu + choix difficulté
        int difficulty = difficultyTreatment();

        //affichage menu scénario (+ bac à sable)

        //une fois toutes les données nécessaires à l'initialisation de l'île
        //Isle isle = new Isle(..., difficulty, ...);

    }

    private static int difficultyTreatment() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        int difficulty = -1;
        do {
            System.out.println(consoleOutput.difficultyMenu());
            String input = clavier.next();
            difficulty = consoleInput.verifyDifficultyChoice(input);
            if (difficulty == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (difficulty < 0);
        return difficulty;
    }

}
