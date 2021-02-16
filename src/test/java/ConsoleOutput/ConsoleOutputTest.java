package ConsoleOutput;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleOutputTest {

    ConsoleOutput consoleOutput = new ConsoleOutput();

    @Test
    public void should_display_rules() {

        String expectedOutput = "Bienvenue sur El Presidente\n";

        assertEquals(expectedOutput, consoleOutput.welcome());
    }

    @Test
    public void should_display_start_game() {

        String expectedOutput = "Début de la partie !\n";

        assertEquals(expectedOutput, consoleOutput.startGame());
    }

    @Test
    public void should_display_end_game() {

        String expectedOutput = "Votre population vous renverse par coup d'état... Fin de la partie.\n";

        assertEquals(expectedOutput, consoleOutput.endGame());
    }

    @Test
    public void should_display_difficulty_menu() {

        String expectedOutput = "Avec quelle difficulté voulez-vous jouer ?\n" +
                "1: Partie facile\n" +
                "2: Partie normale\n" +
                "3: Partie difficile\n";

        assertEquals(expectedOutput, consoleOutput.difficultyMenu().toString());
    }

    @Test
    public void should_display_value_error() {

        String expectedOutput = "Veuillez saisir une valeur numérique supérieure à 0\n";

        assertEquals(expectedOutput, consoleOutput.valueError());
    }

    @Test
    public void should_display_value_of_menu_error() {

        String expectedOutput = "Veuillez saisir une valeur présente dans le menu\n";

        assertEquals(expectedOutput, consoleOutput.valueOfMenuError());
    }

}
