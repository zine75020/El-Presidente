package Game;

import ConsoleOutput.ConsoleOutput;

public class Game {

    public static void main(String[] args) {

        ConsoleOutput consoleOutput = new ConsoleOutput();
        //affichage bienvenue
        System.out.println(consoleOutput.welcome());

        //affichage menu difficulté
        System.out.println(consoleOutput.difficultyMenu());

    }

}
