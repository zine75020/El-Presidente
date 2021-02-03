package ConsoleOutput;

import Output.Output;
import Output.DifficultyChoice;

public class ConsoleOutput implements Output {
    String newLine = System.getProperty("line.separator");

    public String welcome() {
        return "Bienvenue sur El Presidente\n";
    }

    public StringBuilder difficultyMenu() {
        StringBuilder introduction = new StringBuilder("Avec quelle difficulté voulez-vous jouer ?\n");
        int index = 0;
        // ajout de la liste des choix possibles
        for (DifficultyChoice choice : DifficultyChoice.values()) {
            index += 1;
            introduction.append(index).append(": ").append(choice).append("\n");
        }
        return introduction;
    }
}
