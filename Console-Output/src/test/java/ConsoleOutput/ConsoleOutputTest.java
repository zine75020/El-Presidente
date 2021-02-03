package ConsoleOutput;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleOutputTest {

    ConsoleOutput consoleOutput = new ConsoleOutput();

    @Test
    public void should_display_rules() {

        String expectedOutput = "Bienvenue sur El Presidente\n";

        assertEquals(expectedOutput, consoleOutput.welcome());
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
    public void should_display_value_of_menu_error() {

        String expectedOutput = "Veuillez saisir une valeur présente dans le menu\n";

        assertEquals(expectedOutput, consoleOutput.valueOfMenuError());
    }
}
